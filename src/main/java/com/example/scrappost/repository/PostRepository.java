package com.example.scrappost.repository;
import com.example.scrappost.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Optional<Post> findPostByTitle(String title);
    Optional<List<Post>> findByTagsContaining(String tag);
}
