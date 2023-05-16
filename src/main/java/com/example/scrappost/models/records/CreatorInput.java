package com.example.scrappost.models.records;

import com.example.scrappost.models.Address;
import com.example.scrappost.models.enums.Gender;

public record CreatorInput(String firstName, String lastName, String email, Gender gender, int age, String nationality, Address address){}
