package com.example.scrappost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
@SpringBootApplication
@EnableWebSocket
@EnableReactiveMongoRepositories(basePackages = "com.example.scrappost.repository.reactive")
@EnableMongoRepositories(basePackages = "com.example.scrappost.repository")
public class ScrapPostApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScrapPostApplication.class, args);
    }
}
