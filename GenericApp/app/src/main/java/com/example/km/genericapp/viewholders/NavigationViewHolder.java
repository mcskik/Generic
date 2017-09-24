package com.example.km.genericapp.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.km.genericapp.R;
import com.example.km.genericapp.models.navigation.NavigationDrawerItem;

/**
 * NavigationViewHolder Class.
 */
public class NavigationViewHolder {
    private Context context;
    private ImageView imageView;
    private TextView textView;

    public NavigationViewHolder(final Context context, View view) {
        this.context = context;
        initialiseView(view);
    }

    public void bindView(final NavigationDrawerItem navigationDrawerItem) {
        imageView.setImageResource(navigationDrawerItem.icon);
        textView.setText(navigationDrawerItem.name);
    }

    private void initialiseView(View view) {
        imageView = (ImageView) view.findViewById(R.id.imageViewIcon);
        textView = (TextView) view.findViewById(R.id.textViewName);
    }
}
