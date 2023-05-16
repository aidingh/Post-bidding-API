package com.example.scrappost.controllers.reactive;

import com.example.scrappost.models.Creator;
import com.example.scrappost.service.reactive.CreatorReactiveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/api/reactive/v1")
public class CreatorReactiveController {

    private final CreatorReactiveService creatorReactiveService;

    public CreatorReactiveController(CreatorReactiveService creatorReactiveService) {
        this.creatorReactiveService = creatorReactiveService;
    }

    @RequestMapping(value = "/get/creatorsByGraphQlClient", method = RequestMethod.GET)
    public ResponseEntity<Mono<List<Creator>>> getCreatorsByGraphQlClient() {
        return new ResponseEntity<>(creatorReactiveService.getCreatorsByGraphQlClient(), HttpStatus.OK);
    }

    @RequestMapping(value = "/list/creators", method = RequestMethod.GET)
    public ResponseEntity<Flux<Creator>> listAllCreatorEntries(){
        return new ResponseEntity<>(creatorReactiveService.findAllCreators(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get/creator/{id}", method = RequestMethod.GET)
    public ResponseEntity<Mono<Creator>> getCreatorById(@PathVariable String id){
        return new ResponseEntity<>(creatorReactiveService.findById(id), HttpStatus.OK);
    }
}
