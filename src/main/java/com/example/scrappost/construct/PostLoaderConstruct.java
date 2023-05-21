package com.example.scrappost.construct;

import com.example.scrappost.models.Address;
import com.example.scrappost.models.Creator;
import com.example.scrappost.models.Post;
import com.example.scrappost.models.enums.Gender;
import com.example.scrappost.service.CreatorService;
import com.example.scrappost.service.PostService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class PostLoaderConstruct {
    private final CreatorService creatorService;
    private final PostService postService;

    @Value("${app.env}")
    private String environment;
    public PostLoaderConstruct(CreatorService creatorService, PostService postService) {
        this.creatorService = creatorService;
        this.postService = postService;
    }

    public void insertDataIntoDB(){
        Post setHairOnFirePOst = new Post("HOW TO NOT SET YOUR HAIR ON FIRE", "Tutorial on how to not lose your hair", Stream.of("Baldness", "A lot of hair", "Prevent hair loss").collect(Collectors.toList()), 0);
        Post dadJokePost = new Post("BEST DAD JOKES OF 2023", "Where do fruits go on vacation? Pear-is!", Stream.of("Dad-jokes", "Funny", "2023").collect(Collectors.toList()), 100);
        Post magnusDidNothingWrongPost = new Post("MAGNUS DID NOTHING WRONG", "We all know that Magnus just wanted to help the emperor", Stream.of("Warhammer 40k", "Ultra-marines-lol", "Horus").collect(Collectors.toList()), 20);

        postService.createPost(setHairOnFirePOst);
        postService.createPost(dadJokePost);
        postService.createPost(magnusDidNothingWrongPost);

        Creator creator = new Creator("Aidin", "Loi", "kelb@hotmail.com", Gender.MALE, 32, 3, "Iran", new Address("Iran", "Tehran", "1337"));
        creator.setPost(Arrays.asList(setHairOnFirePOst, dadJokePost, magnusDidNothingWrongPost));
        creatorService.saveCreator(creator);
    }

    public void listAllPostContent(){
        System.out.println(" ---- Posts ---- ");
        postService.findAllPostsTest().forEach(post -> System.out.println(post.content));
        List<Post> result =  postService.findAllPostsTest().stream().filter(post -> post.content.startsWith("L")).toList();
        result.forEach(post -> System.out.println(post.tags));
    }

    public void getCreatorById(String id){
        Creator creator = creatorService.findById(id);
        System.out.println(creator.nationality);
    }

    @PostConstruct
    private void init() {
        System.out.println(environment);
        if(creatorService.isCreatorCollectionEmpty()){
            System.out.println("---- DB inserting initiated ----");
            insertDataIntoDB();
        }
        listAllPostContent();
    }
}
