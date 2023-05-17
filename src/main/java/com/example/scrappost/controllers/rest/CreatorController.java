package com.example.scrappost.controllers.rest;

import com.example.scrappost.models.Creator;
import com.example.scrappost.models.records.CreatorInput;
import com.example.scrappost.models.records.PostInput;
import com.example.scrappost.service.CreatorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CreatorController {
    private final CreatorService creatorService;

    public CreatorController(CreatorService creatorService) {
        this.creatorService = creatorService;
    }

    @RequestMapping(value = "/save/creator", method = RequestMethod.POST)
    public Creator saveCreator(@RequestBody Creator creator) {
        return creatorService.saveCreator(creator);
    }

    @MutationMapping
    public Creator addCreator(@Argument("creator") CreatorInput creatorInput){
        Creator creator = new Creator(creatorInput.firstName(), creatorInput.lastName(), creatorInput.email(), creatorInput.gender(), creatorInput.age(), creatorInput.nationality(),creatorInput.address());
        return creatorService.saveCreator(creator);
    }

    @MutationMapping
    public Creator addCreatorWithPosts(@Argument("creator") CreatorInput creatorInput, @Argument("posts") List<PostInput> postInput){
        return creatorService.saveCreatorWithPosts(creatorInput, postInput);
    }

    @QueryMapping
    public Creator findCreator(@Argument String id){
        return creatorService.findById(id);
    }

    @QueryMapping
    public List<Creator> allCreators(){
        return creatorService.findAllCreators();
    }


}
