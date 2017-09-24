package com.example.km.genericapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.km.genericapp.models.navigation.NavigationDrawerItem;
import com.example.km.genericapp.viewholders.NavigationViewHolder;

/**
 * NavigationCustomAdapter Class.
 */
public class NavigationCustomAdapter extends ArrayAdapter<NavigationDrawerItem> {

    Context context;
    int layoutResourceId;
    NavigationDrawerItem data[] = null;

    public NavigationCustomAdapter(Context context, int layoutResourceId, NavigationDrawerItem[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context
                    .LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(layoutResourceId, parent, false);
            NavigationViewHolder navigationViewHolder = new NavigationViewHolder(getContext(), rowView);
            rowView.setTag(navigationViewHolder);
        }
        final NavigationDrawerItem navigationDrawerItem = getItem(position);
        NavigationViewHolder navigationViewHolder = (NavigationViewHolder) rowView.getTag();
        navigationViewHolder.bindView(navigationDrawerItem);
        return rowView;
    }
}
