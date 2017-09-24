package com.example.km.genericapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.km.genericapp.R;
import com.example.km.genericapp.constants.Constants;
import com.example.km.genericapp.models.posts.BlogItem;
import com.example.km.genericapp.viewholders.PostCardViewHolder;

import java.util.ArrayList;

/**
 * Post card adapter.
 */
public class PostCardAdapter extends RecyclerView.Adapter<PostCardViewHolder> {

    private final ArrayList<BlogItem> blogItems;

    public PostCardAdapter(ArrayList<BlogItem> blogItem) {
        this.blogItems = blogItem;
    }

    @Override
    public PostCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.post_card_item, parent, false);
        return new PostCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostCardViewHolder holder, int position) {
        final BlogItem blogItem = blogItems.get(position);
        holder.titleTextView.setText(blogItem.getPost().getTitle());
        String body = blogItem.getPost().getBody();
        body = body.replace(Constants.NEWLINE, Constants.SPACE);
        holder.bodyTextView.setText(body);
        holder.userNameTextView.setText(blogItem.getUser().getUsername());
        holder.numberOfCommentsTextView.setText(String.valueOf(blogItem.getComments().size()));
    }

    @Override
    public int getItemCount() {
        if (blogItems == null) {
            return 0;
        } else {
            return blogItems.size();
        }
    }
}
