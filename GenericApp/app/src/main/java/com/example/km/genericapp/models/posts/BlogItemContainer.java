package com.example.km.genericapp.models.posts;

import com.example.km.genericapp.network.PostalApiService;
import com.example.km.genericapp.utilities.EmbeddedResourceHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class BlogItemContainer {

    public ArrayList<BlogItem> blogItems;

    public ArrayList<BlogItem> getBlogItems() {
        return blogItems;
    }

    /**
     * Return an object from a file which contains a JSON string representing a single item.
     */
    public Post getPost(String fileName) {
        Gson gson = new Gson();
        String json = EmbeddedResourceHelper.getInstance().ReadContentFromTestResources(fileName);
        return gson.fromJson(json, Post.class);
    }

    /**
     * Return an ArrayList of objects from a file which contains a JSON string representing an array of the specified type.
     */
    public ArrayList<Post> getPosts(String fileName) {
        Gson gson = new Gson();
        String json = EmbeddedResourceHelper.getInstance().ReadContentFromTestResources(fileName);
        Type type = new TypeToken<ArrayList<Post>>() {
        }.getType();
        ArrayList<Post> posts = new Gson().fromJson(json, type);
        return posts;
    }

    /**
     * Return an ArrayList of objects from a file which contains a JSON string representing an array of the specified type.
     */
    public ArrayList<User> getUsers(String fileName) {
        Gson gson = new Gson();
        String json = EmbeddedResourceHelper.getInstance().ReadContentFromTestResources(fileName);
        Type type = new TypeToken<ArrayList<User>>() {
        }.getType();
        ArrayList<User> users = new Gson().fromJson(json, type);
        return users;
    }

    /**
     * Return an ArrayList of objects from a file which contains a JSON string representing an array of the specified type.
     */
    public ArrayList<Comment> getComments(String fileName) {
        Gson gson = new Gson();
        String json = EmbeddedResourceHelper.getInstance().ReadContentFromTestResources(fileName);
        Type type = new TypeToken<ArrayList<Comment>>() {
        }.getType();
        ArrayList<Comment> comments = new Gson().fromJson(json, type);
        return comments;
    }

    public ArrayList<BlogItem> getBlogItems(ArrayList<Post> posts, ArrayList<User> users, ArrayList<Comment> comments) {
        ArrayList<BlogItem> blogItems = new ArrayList<>();
        for (Post post : posts) {
            User user = users.get(post.getUserId() - 1);
            ArrayList<Comment> commentsForPost = getCommentsByPostId(comments, post.getId());
            blogItems.add(new BlogItem(post, user, commentsForPost));
        }
        return blogItems;
    }

    public ArrayList<Comment> getCommentsByPostId(ArrayList<Comment> allComments, int postId) {
        ArrayList<Comment> commentsForPost = new ArrayList<>();
        for (Comment comment : allComments) {
            if (comment.getPostId() == postId) {
                commentsForPost.add(comment);
            }
        }
        return commentsForPost;
    }

    public Comment getCommentById(ArrayList<Comment> allComments, int id) {
        ArrayList<Comment> commentsForPost = new ArrayList<>();
        int index = 0;
        for (Comment comment : allComments) {
            if (comment.getId() == id) {
                return allComments.get(index);
            }
            index++;
        }
        return null;
    }

    public void loadBlogItems() {
        PostalApiService.getBlogItems()
                .subscribeOn(Schedulers.single())
                .observeOn(Schedulers.single())
                .subscribeWith(new DisposableObserver<ArrayList<BlogItem>>() {
                    @Override
                    public void onComplete() {
                        handleComplete();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        handleError(throwable);
                    }

                    @Override
                    public void onNext(ArrayList<BlogItem> blogItems) {
                        handleBlogDataResponse2(blogItems);
                    }
                });
        delay(2000);
    }

    private void handleBlogDataResponse2(ArrayList<BlogItem> blogItems) {
        this.blogItems = blogItems;
    }

    private void handleError(Throwable error) {
        String temp = "Error";
    }

    private void handleComplete() {
        String temp = "Complete";
    }

    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
        }
    }
}
