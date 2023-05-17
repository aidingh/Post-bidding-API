package com.example.scrappost.controllers.rest;

import com.example.scrappost.models.Post;
import com.example.scrappost.service.PostService;
import com.example.scrappost.subscriptions.SubscriptionPostResolver;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    private final PostService postService;
    private final SubscriptionPostResolver subscriptionPostResolver;
    public PostController(PostService postService, SubscriptionPostResolver subscriptionPostResolver) {
        this.postService = postService;
        this.subscriptionPostResolver = subscriptionPostResolver;
    }

    @RequestMapping(value = "/create/post", method = RequestMethod.POST)
    public Post createPost(@RequestBody Post post) {
        return null;
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
        return subscriptionPostResolver.newPostStreams();
    }
}
