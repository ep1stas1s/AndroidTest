package com.yjp.collectiontest.model;

public class PostItem {

    private String userName;
    private String postImgUrl;
    private String postText;
    private int postLikeCount;
    private boolean isUserLike;

    public PostItem(String userName, String postImgUrl, String postText, int postLikeCount, boolean isUserLike) {
        this.userName = userName;
        this.postImgUrl = postImgUrl;
        this.postText = postText;
        this.postLikeCount = postLikeCount;
        this.isUserLike = isUserLike;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostImgUrl() {
        return postImgUrl;
    }

    public void setPostImgUrl(String postImgUrl) {
        this.postImgUrl = postImgUrl;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public int getPostLikeCount() {
        return postLikeCount;
    }

    public void setPostLikeCount(int postLikeCount) {
        this.postLikeCount = postLikeCount;
    }

    public boolean isUserLike() {
        return isUserLike;
    }

    public void setUserLike(boolean userLike) {
        isUserLike = userLike;
    }
}
