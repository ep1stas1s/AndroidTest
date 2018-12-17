package com.yjp.unhappiness.post.model;

public class Post {
    private String userId;
    private String postImage;
    private String context;
    private int tearCount;

    public Post(String userId, String postImage, String context, int tearCount) {
        this.userId = userId;
        this.postImage = postImage;
        this.context = context;
        this.tearCount = tearCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getTearCount() {
        return tearCount;
    }

    public void setTearCount(int tearCount) {
        this.tearCount = tearCount;
    }
}
