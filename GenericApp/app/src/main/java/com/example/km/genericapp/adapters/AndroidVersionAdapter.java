package com.example.km.genericapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.km.genericapp.R;
import com.example.km.genericapp.models.versions.AndroidVersion;
import com.example.km.genericapp.viewholders.AndroidVersionViewHolder;

import java.util.ArrayList;

/**
 * Android version adapter.
 */
public class AndroidVersionAdapter extends RecyclerView.Adapter<AndroidVersionViewHolder> {

    private final ArrayList<AndroidVersion> versions;

    public AndroidVersionAdapter(ArrayList<AndroidVersion> versions) {
        this.versions = versions;
    }

    @Override
    public AndroidVersionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.version_item, parent, false);
        return new AndroidVersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AndroidVersionViewHolder holder, int position) {
        final AndroidVersion version = versions.get(position);
        holder.versionNameTextView.setText(version.getName());
        holder.versionNumberTextView.setText(version.getVer());
        String apiLevel = String.format("(%s)", version.getApi());
        holder.apiLevelTextView.setText(apiLevel);
    }

    @Override
    public int getItemCount() {
        if (versions == null) {
            return 0;
        } else {
            return versions.size();
        }
    }
}
