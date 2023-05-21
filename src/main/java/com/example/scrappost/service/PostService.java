package com.example.scrappost.service;

import com.example.scrappost.models.Post;
import com.example.scrappost.models.PostImage;
import com.example.scrappost.repository.PostImageRepository;
import com.example.scrappost.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostImageRepository postImageRepository;

    public PostService(PostRepository postRepository, PostImageRepository postImageRepository) {
        this.postRepository = postRepository;
        this.postImageRepository = postImageRepository;
    }

    public Post createPost(Post post) {
        post.setCreationStamp(new Date());
        return postRepository.save(post);
    }

    public void createPostWithImage(Post post, MultipartFile imageFile) throws IOException {
        PostImage image = new PostImage();
        image.setFileName(imageFile.getOriginalFilename());
        image.setContentType(imageFile.getContentType());
        image.setData(imageFile.getBytes());

        PostImage savedImage = postImageRepository.save(image);
        post.setImage(savedImage);

       postRepository.save(post);
    }

    public ResponseEntity<Post> updateById(Post newPost, String id) throws IllegalAccessException {
        Post retrivedPost = postRepository.findById(id).orElseThrow(NoSuchElementException::new);

        Field[] fields = newPost.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object newValue = field.get(newPost);

            if (newValue != null) {
                field.set(retrivedPost, newValue);
            }
        }
        retrivedPost.setLastUpdatedDate(new Date());
        return new ResponseEntity<>(postRepository.save(retrivedPost), HttpStatus.OK);
    }

    public Post getPostById(String id){
        return postRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public Post findByTitle(String title){
        return postRepository.findPostByTitle(title).orElseThrow(NoSuchElementException::new);
    }

    public List<Post> findByTag(String tag){
        return postRepository.findByTagsContaining(tag).orElseThrow(NoSuchElementException::new);
    }

    public ResponseEntity<List<Post>> findAllPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    public List<Post> findAllPostsTest() {
        return postRepository.findAll();
    }

}