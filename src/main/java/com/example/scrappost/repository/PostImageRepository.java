package com.example.scrappost.repository;

import com.example.scrappost.models.PostImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepository extends MongoRepository<PostImage, String> {
}
