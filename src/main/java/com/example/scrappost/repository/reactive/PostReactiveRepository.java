package com.example.scrappost.repository.reactive;

import com.example.scrappost.models.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PostReactiveRepository extends ReactiveMongoRepository<Post, String> {
}
