package com.example.scrappost.clients;

import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

@Component
public class GraphQlClient {
    @Bean
    public HttpGraphQlClient httpGraphQlCLient(){
        return HttpGraphQlClient.builder().url("http://localhost:8080/graphql").build();
    }
}
