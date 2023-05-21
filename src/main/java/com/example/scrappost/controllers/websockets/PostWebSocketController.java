package com.example.scrappost.controllers.websockets;

import com.example.scrappost.models.Post;
import com.example.scrappost.repository.PostRepository;
import com.example.scrappost.repository.reactive.PostReactiveRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;
@Controller
public class PostWebSocketController {
    private final PostReactiveRepository postReactiveRepository;
    private final PostRepository postRepository;
    public PostWebSocketController(PostReactiveRepository postReactiveRepository, PostRepository postRepository) {
        this.postReactiveRepository = postReactiveRepository;
        this.postRepository = postRepository;
    }
    @MessageMapping("posts")
    public Flux<Post> getPosts() {
        return postReactiveRepository.findAll();
    }
    @MessageMapping("post-stream")
    public Flux<List<Post>> newPostStreams() {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(i -> Flux.fromIterable(postRepository.findAll()).collectList());
    }
    @MessageMapping("post-stream-bid")
    public Flux<Integer> newPostStreamBid() {
        return Flux.interval(Duration.ofSeconds(1))
                        .flatMap(i -> postReactiveRepository.findAll().map(Post::getBidPrice));
    }
}
