package com.example.scrappost.subscriptions;

import com.example.scrappost.models.Post;
import com.example.scrappost.repository.PostRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.List;
@Component
public class SubscriptionPostResolver{

    private final PostRepository postRepository;

    public SubscriptionPostResolver(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Flux<List<Post>> newPostStreams() {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(i -> Flux.fromIterable(postRepository.findAll()).collectList());
    }
}
