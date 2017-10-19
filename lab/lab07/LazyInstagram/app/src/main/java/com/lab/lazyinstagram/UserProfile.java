package com.lab.lazyinstagram;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 10/6/2017 AD.
 */

public class UserProfile {

    private String user;
    private String urlProfile;
    private String follower;
    private String following;
    private String post;
    private String bio;
    @SerializedName("posts") private List<Posts> itemPost = new ArrayList<>();

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Posts> getItemPost() {
        return itemPost;
    }

    public void setItemPost(List<Posts> itemPost) {
        this.itemPost = itemPost;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

}