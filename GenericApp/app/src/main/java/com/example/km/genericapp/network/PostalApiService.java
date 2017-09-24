package com.example.km.genericapp.network;

import com.example.km.genericapp.constants.Urls;
import com.example.km.genericapp.models.posts.BlogData;
import com.example.km.genericapp.models.posts.BlogItem;
import com.example.km.genericapp.models.posts.Comment;
import com.example.km.genericapp.models.posts.Post;
import com.example.km.genericapp.models.posts.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * JSON placeholder API service.
 */
public class PostalApiService {
    private static PostalApiInterface retrofitService;

    private static PostalApiInterface getService() {
        if (retrofitService == null) {
            retrofitService = new Retrofit.Builder()
                    .baseUrl(Urls.JSON_PLACEHOLDER_API_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(PostalApiInterface.class);
        }
        return retrofitService;
    }

    public static Observable<ArrayList<Post>> getPosts() {
        return getService().getPosts();
    }

    public static Observable<ArrayList<User>> getUsers() {
        return getService().getUsers();
    }

    public static Observable<ArrayList<Comment>> getComments() {
        return getService().getComments();
    }

    public static Observable<ArrayList<BlogItem>> getBlogItems() {
        Observable<ArrayList<Post>> observablePosts = getService().getPosts().subscribeOn(Schedulers.newThread());
        Observable<ArrayList<User>> observableUsers = getService().getUsers().subscribeOn(Schedulers.newThread());
        Observable<ArrayList<Comment>> observableComments = getService().getComments().subscribeOn(Schedulers.newThread());
        return Observable.zip(observablePosts, observableUsers, observableComments,
                new Function3<ArrayList<Post>, ArrayList<User>, ArrayList<Comment>, BlogData>() {
                    @Override
                    public BlogData apply(ArrayList<Post> posts, ArrayList<User> users, ArrayList<Comment> comments) {
                        return new BlogData(posts, users, comments);
                    }
                }).map(new Function<BlogData, ArrayList<BlogItem>>() {
            @Override
            public ArrayList<BlogItem> apply(BlogData blogData) throws Exception {
                return getBlogItems(blogData);
            }
        });
    }

    private static ArrayList<BlogItem> getBlogItems(BlogData blogData) {
        ArrayList<BlogItem> blogItems = new ArrayList<>();
        for (Post post : blogData.getPosts()) {
            User user = blogData.getUsers().get(post.getUserId() - 1);
            ArrayList<Comment> commentsForPost = getCommentsByPostId(blogData.getComments(), post.getId());
            blogItems.add(new BlogItem(post, user, commentsForPost));
        }
        return blogItems;
    }

    private static ArrayList<Comment> getCommentsByPostId(ArrayList<Comment> allComments, int postId) {
        ArrayList<Comment> commentsForPost = new ArrayList<>();
        for (Comment comment : allComments) {
            if (comment.getPostId() == postId) {
                commentsForPost.add(comment);
            }
        }
        return commentsForPost;
    }

    public interface PostalApiInterface {
        @GET("posts/{id}")
        Observable<Post> getPost(@Path("id") int id);

        @GET("posts/")
        Observable<ArrayList<Post>> getPosts();

        @GET("users/")
        Observable<ArrayList<User>> getUsers();

        @GET("comments/")
        Observable<ArrayList<Comment>> getComments();
    }
}
