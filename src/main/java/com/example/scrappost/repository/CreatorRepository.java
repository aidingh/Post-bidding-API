package com.example.scrappost.repository;

import com.example.scrappost.models.Creator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CreatorRepository extends MongoRepository<Creator, String> {
        Optional<Creator> findCreatorByFirstName(String name);
}
