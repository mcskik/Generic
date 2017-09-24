package com.example.km.genericapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.km.genericapp.R;

/**
 * Post card view holder.
 */
public class PostCardViewHolder extends RecyclerView.ViewHolder {

    public final TextView titleTextView;
    public final TextView bodyTextView;
    public final TextView userNameTextView;
    public final TextView numberOfCommentsTextView;

    public PostCardViewHolder(View view) {
        super(view);
        this.titleTextView = (TextView) itemView.findViewById(R.id.title);
        this.bodyTextView = (TextView) itemView.findViewById(R.id.body);
        this.userNameTextView = (TextView) itemView.findViewById(R.id.userName);
        this.numberOfCommentsTextView = (TextView) itemView.findViewById(R.id.numberOfComments);
    }
}
