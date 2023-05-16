package com.example.scrappost.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "images")
public class PostImage {
    @Id
    public String id;
    public byte[] data;
}
