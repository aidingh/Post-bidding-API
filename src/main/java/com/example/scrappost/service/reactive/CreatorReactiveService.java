package com.example.scrappost.service.reactive;

import com.example.scrappost.clients.GraphQlClient;
import com.example.scrappost.models.Creator;
import com.example.scrappost.repository.reactive.CreatorReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class CreatorReactiveService {
    private final CreatorReactiveRepository creatorReactiveRepository;
    private final GraphQlClient graphQlClient;

    public CreatorReactiveService(CreatorReactiveRepository creatorReactiveRepository, GraphQlClient graphQlClient) {
        this.creatorReactiveRepository = creatorReactiveRepository;
        this.graphQlClient = graphQlClient;
    }
    public Flux<Creator> findAllCreators(){
        return creatorReactiveRepository.findAll();
    }

    public Mono<List<Creator>> getCreatorsByGraphQlClient() {
        String graphQlDocument = buildGraphQlQueryDocument("firstName","lastName", "post { title, content, tags }");
        return graphQlClient.httpGraphQlCLient()
                .document(graphQlDocument)
                .retrieve("allCreators")
                .toEntityList(Creator.class);
    }
    public Mono<Creator> findById(String id){
        return creatorReactiveRepository.findById(id).switchIfEmpty(Mono.error(new NoSuchElementException()));
    }

    public String buildGraphQlQueryDocument(String... fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("query {\n");
        sb.append("  allCreators {\n");
        for (String field : fields) {
            sb.append("    ").append(field).append("\n");
        }
        sb.append("  }\n");
        sb.append("}");
        return sb.toString();
    }

}
