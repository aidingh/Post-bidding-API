package com.example.scrappost.service;

import com.example.scrappost.models.Creator;
import com.example.scrappost.models.Post;
import com.example.scrappost.models.records.CreatorInput;
import com.example.scrappost.models.records.PostInput;
import com.example.scrappost.repository.CreatorRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import static org.springframework.data.mongodb.core.query.Criteria.where;
@Service
public class CreatorService {
    private final CreatorRepository creatorRepository;
    private final PostService postService;
    private final MongoTemplate mongoTemplate;

    public CreatorService(CreatorRepository creatorRepository, PostService postService, MongoTemplate mongoTemplate) {
        this.creatorRepository = creatorRepository;
        this.postService = postService;
        this.mongoTemplate = mongoTemplate;
    }
    public Creator saveCreator(Creator creator) {
        creator.setCreationStamp(new Date());
        return creatorRepository.save(creator);
    }
    public Creator saveCreatorWithPosts(CreatorInput creatorInput, List<PostInput> postInput){

        List<Post> posts = postInput.stream()
                .map(input -> new Post(input.title(), input.content(), input.tags(), input.bidPrice()))
                .peek(postService::createPost)
                .collect(Collectors.toList());

        Creator creator = new Creator(creatorInput.firstName(), creatorInput.lastName(), creatorInput.email(),
                creatorInput.gender(), creatorInput.age(), creatorInput.nationality(),creatorInput.address());

        creator.setPost(posts);
        creator.setAmountOfPosts(postInput.size());
        return saveCreator(creator);
    }

    public List<Creator> findAllCreators(){
        return creatorRepository.findAll();
    }
    public Creator findById(String id){
        return creatorRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    public Creator findCreatorByFirstName(String name){
        return creatorRepository.findCreatorByFirstName(name).orElseThrow(NoSuchElementException::new);
    }
    public List<Creator> queryByEmail(String email) {
        Query query = new Query();
        query.addCriteria(where("email").is(email));
        List<Creator> creators = mongoTemplate.find(query, Creator.class);

        if(creators.isEmpty()){
            throw new NoSuchElementException();
        }
        return creators;
    }

    public boolean isCreatorCollectionEmpty(){
        long count = creatorRepository.count();
        return count == 0;
    }
}
