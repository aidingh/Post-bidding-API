package com.example.scrappost.service.reactive;

import com.example.scrappost.models.Post;
import com.example.scrappost.repository.PostRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;
@Service
public class PostSubscription {
    private final PostRepository postRepository;
    public PostSubscription(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public Flux<List<Post>> newPostStreams() {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(i -> Flux.fromIterable(postRepository.findAll()).collectList());
    }
}
