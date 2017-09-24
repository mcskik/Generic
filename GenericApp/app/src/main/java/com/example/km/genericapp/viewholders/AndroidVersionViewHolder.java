package com.example.km.genericapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.km.genericapp.R;

/**
 * Android version view holder.
 */
public class AndroidVersionViewHolder extends RecyclerView.ViewHolder {

    public final TextView versionNameTextView;
    public final TextView versionNumberTextView;
    public final TextView apiLevelTextView;

    public AndroidVersionViewHolder(View view) {
        super(view);
        versionNameTextView = (TextView) view.findViewById(R.id.versionName);
        versionNumberTextView = (TextView) view.findViewById(R.id.versionNumber);
        apiLevelTextView = (TextView) view.findViewById(R.id.apiLevel);
    }
}
