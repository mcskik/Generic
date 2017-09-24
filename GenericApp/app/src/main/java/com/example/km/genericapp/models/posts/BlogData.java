package com.example.km.genericapp.models.posts;

import java.util.ArrayList;

/**
 * Container for all blog dsta.
 */
public class BlogData {

    private ArrayList<Post> posts;
    private ArrayList<User> users;
    private ArrayList<Comment> comments;

    public BlogData(ArrayList<Post> posts, ArrayList<User> users, ArrayList<Comment> comments) {
        this.posts = posts;
        this.users = users;
        this.comments = comments;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
