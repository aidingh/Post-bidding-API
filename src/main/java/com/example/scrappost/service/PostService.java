package com.example.scrappost.service;

import com.example.scrappost.models.Post;
import com.example.scrappost.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        post.setCreationStamp(new Date());
        return postRepository.save(post);
    }

    public Post findByTitle(String title){
        return postRepository.findPostByTitle(title).orElseThrow(NoSuchElementException::new);
    }

    public List<Post> findByTag(String tag){
        return postRepository.findByTagsContaining(tag).orElseThrow(NoSuchElementException::new);
    }

    public ResponseEntity<List<Post>> findAllPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    public List<Post> findAllPostsTest() {
        return postRepository.findAll();
    }

}