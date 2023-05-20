package com.example.scrappost.models.wrappers;

import com.example.scrappost.models.Post;
import org.springframework.web.multipart.MultipartFile;

public class PostWrapper {
    private Post post;
    private MultipartFile imageFile;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }
}
