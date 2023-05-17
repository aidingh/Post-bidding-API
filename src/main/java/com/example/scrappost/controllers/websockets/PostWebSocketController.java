package com.example.scrappost.controllers.websockets;

import com.example.scrappost.models.Post;
import com.example.scrappost.repository.reactive.PostReactiveRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Controller
public class PostWebSocketController {

    private final PostReactiveRepository postReactiveRepository;

    public PostWebSocketController(PostReactiveRepository postReactiveRepository) {
        this.postReactiveRepository = postReactiveRepository;
    }

    @MessageMapping("posts")
    public Flux<Post> getPosts() {
        return postReactiveRepository.findAll();
    }
}
