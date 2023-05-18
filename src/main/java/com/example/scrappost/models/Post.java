package com.example.scrappost.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;
@Document(collection = "posts")
public class Post {

    @Id
    public String id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    public String title;
    @NotNull
    @NotBlank
    @Size(max = 1000)
    public String content;
    public String imageId;
    public List<String> tags;
    public Date creationStamp;
    public Date lastUpdatedDate;
    public int numberOfUpvote;

    public int bidPrice;
    @DBRef
    public PostImage image;

    public Post() {}

    public Post(String title, String content, List<String> tags, int bidPrice) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.bidPrice = bidPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getCreationStamp() {
        return creationStamp;
    }

    public void setCreationStamp(Date creationStamp) {
        this.creationStamp = creationStamp;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public int getNumberOfUpvote() {
        return numberOfUpvote;
    }

    public void setNumberOfUpvote(int numberOfUpvote) {
        this.numberOfUpvote = numberOfUpvote;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }

    public PostImage getImage() {
        return image;
    }

    public void setImage(PostImage image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageId='" + imageId + '\'' +
                ", tags=" + tags +
                ", creationStamp=" + creationStamp +
                ", lastUpdatedDate=" + lastUpdatedDate +
                ", numberOfUpvote=" + numberOfUpvote +
                ", bidPrice=" + bidPrice +
                ", image=" + image +
                '}';
    }
}
