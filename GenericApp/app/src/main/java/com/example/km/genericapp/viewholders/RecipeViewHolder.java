package com.example.km.genericapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.km.genericapp.R;

/**
 * Recipe view holder.
 */
public class RecipeViewHolder extends RecyclerView.ViewHolder {

    public final TextView titleTextView;

    public RecipeViewHolder(View view) {
        super(view);
        titleTextView = (TextView) itemView.findViewById(R.id.title);
    }
}
