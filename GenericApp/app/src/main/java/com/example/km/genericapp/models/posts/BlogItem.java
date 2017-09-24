package com.example.km.genericapp.models.posts;

import java.util.ArrayList;

/**
 * Blog item containing all data related to a post.
 */
public class BlogItem {

    private Integer id;
    private Post post;
    private User user;
    private ArrayList<Comment> comments;

    public BlogItem(Post post, User user, ArrayList<Comment> comments) {
        this.id = post.getId();
        this.post = post;
        this.user = user;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
