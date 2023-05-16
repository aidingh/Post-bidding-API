package com.example.scrappost.repository.reactive;

import com.example.scrappost.models.Creator;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CreatorReactiveRepository extends ReactiveMongoRepository<Creator, String> {
}
