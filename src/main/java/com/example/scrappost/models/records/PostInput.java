package com.example.scrappost.models.records;

import java.util.List;

public record PostInput(String title, String content, List<String> tags){}
