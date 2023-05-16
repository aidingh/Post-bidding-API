package com.example.scrappost.utils;

public class ExceptionHandler {
    public void objectIsNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object cant be null");
        }
    }
}
