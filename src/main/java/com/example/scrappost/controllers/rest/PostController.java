package com.example.scrappost.controllers.rest;

import com.example.scrappost.models.Post;
import com.example.scrappost.service.PostService;
import com.example.scrappost.service.reactive.PostSubscription;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    private final PostService postService;
    private final PostSubscription postSubscription;
    public PostController(PostService postService, PostSubscription postSubscription) {
        this.postService = postService;
        this.postSubscription = postSubscription;
    }

    @RequestMapping(value = "/create/post", method = RequestMethod.POST)
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @RequestMapping(value = "/create/post-image", method = RequestMethod.POST)
    public ResponseEntity<String> createPostWithImage(@RequestBody Post post, @RequestParam("image") MultipartFile imageFile) {
        try {
            postService.createPostWithImage(post, imageFile);
            return ResponseEntity.ok("Post created successfully with image.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create post with image.");
        }
    }

    @QueryMapping
    public List<Post> allPosts(){
        return postService.findAllPostsTest();
    }

    @RequestMapping(value = "/list/posts", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> listBlogEntries(){
        return postService.findAllPosts();
    }

    @SubscriptionMapping
    public Flux<List<Post>> newPost() {
        return postSubscription.newPostStreams();
    }
}
