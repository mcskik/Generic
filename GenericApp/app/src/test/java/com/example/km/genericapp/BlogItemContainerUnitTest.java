package com.example.km.genericapp;

import com.example.km.genericapp.models.posts.BlogItem;
import com.example.km.genericapp.models.posts.BlogItemContainer;
import com.example.km.genericapp.models.posts.Comment;
import com.example.km.genericapp.models.posts.Post;
import com.example.km.genericapp.models.posts.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * BlogItem container unit test.
 */
public class BlogItemContainerUnitTest {

    @Before
    public void setup() throws Exception {
    }

    @Test
    public void readAndParseJsonTest() throws Exception {
        BlogItemContainer container = new BlogItemContainer();
        ArrayList<Post> posts = container.getPosts("posts.json");
        ArrayList<User> users = container.getUsers("users.json");
        ArrayList<Comment> comments = container.getComments("comments.json");
        assertNotNull(posts);
        assertNotNull(users);
        assertNotNull(comments);
        assertTrue(posts.size() == 100);
        assertTrue(users.size() == 10);
        assertTrue(comments.size() == 500);
    }

    @Test
    public void populateBlogItemsTest() throws Exception {
        BlogItemContainer container = new BlogItemContainer();
        ArrayList<Post> posts = container.getPosts("posts.json");
        ArrayList<User> users = container.getUsers("users.json");
        ArrayList<Comment> comments = container.getComments("comments.json");
        ArrayList<BlogItem> blogItems = container.getBlogItems(posts, users, comments);
        assertNotNull(posts);
        assertNotNull(users);
        assertNotNull(comments);
        assertNotNull(blogItems);
        assertTrue(posts.size() == 100);
        assertTrue(users.size() == 10);
        assertTrue(comments.size() == 500);
        assertTrue(blogItems.size() == 100);
    }

    @Test
    public void populateBlogItemsZipMapTest() throws Exception {
        BlogItemContainer container = new BlogItemContainer();
        container.loadBlogItems();
        ArrayList<BlogItem> blogItems = container.getBlogItems();
        assertNotNull(blogItems);
        assertTrue(blogItems.size() == 100);
    }

    @Test
    public void compareBlogItemsFromBothSourcesTest() throws Exception {
        BlogItemContainer container = new BlogItemContainer();
        ArrayList<Post> posts = container.getPosts("posts.json");
        ArrayList<User> users = container.getUsers("users.json");
        ArrayList<Comment> comments = container.getComments("comments.json");
        ArrayList<BlogItem> blogItemsFromFiles = container.getBlogItems(posts, users, comments);
        container.loadBlogItems();
        ArrayList<BlogItem> blogItemsFromApi = container.getBlogItems();
        compare(blogItemsFromFiles, blogItemsFromApi);
    }

    private void compare(ArrayList<BlogItem> blogItemsFromFiles, ArrayList<BlogItem> blogItemsFromApi) {
        for (BlogItem blogItemFromFile : blogItemsFromFiles) {
            // Set all objects for comparison.
            Post postFromFile = blogItemFromFile.getPost();
            User userFromFile = blogItemFromFile.getUser();
            ArrayList<Comment> commentsFromFile = blogItemFromFile.getComments();
            BlogItem blogItemFromApi = blogItemsFromApi.get(blogItemFromFile.getId() - 1);
            Post postFromApi = blogItemFromApi.getPost();
            User userFromApi = blogItemFromApi.getUser();
            ArrayList<Comment> commentsFromApi = blogItemFromApi.getComments();

            // Make assertions.
            // Blog item.
            assertEquals(blogItemFromFile.getId(), blogItemFromApi.getId());

            // Post.
            assertEquals(postFromFile.getId(), postFromApi.getId());
            assertEquals(postFromFile.getUserId(), postFromApi.getUserId());
            assertEquals(postFromFile.getTitle(), postFromApi.getTitle());
            assertEquals(postFromFile.getBody(), postFromApi.getBody());

            // User.
            assertEquals(userFromFile.getId(), userFromApi.getId());
            assertEquals(userFromFile.getName(), userFromApi.getName());
            assertEquals(userFromFile.getUsername(), userFromApi.getUsername());
            assertEquals(userFromFile.getEmail(), userFromApi.getEmail());

            // Comments.
            BlogItemContainer container = new BlogItemContainer();
            for (Comment commentFromFile : commentsFromFile) {
                // Set all objects for comparison.
                Comment commentFromApi = null;
                try {
                    commentFromApi = container.getCommentById(commentsFromApi, commentFromFile.getId());
                    assertNotNull(commentFromApi);
                } catch (Exception ex) {
                    String temp = ex.getMessage();
                }
                // Make assertions.
                assertEquals(commentFromFile.getPostId(), commentFromApi.getPostId());
                assertEquals(commentFromFile.getId(), commentFromApi.getId());
                assertEquals(commentFromFile.getName(), commentFromApi.getName());
                assertEquals(commentFromFile.getEmail(), commentFromApi.getEmail());
                assertEquals(commentFromFile.getBody(), commentFromApi.getBody());
            }
        }
    }
}
