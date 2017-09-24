package com.example.km.genericapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.km.genericapp.R;
import com.example.km.genericapp.constants.Constants;
import com.example.km.genericapp.models.recipes.Recipes;
import com.example.km.genericapp.models.recipes.Results;
import com.example.km.genericapp.viewholders.RecipeViewHolder;

/**
 * Recipe adapter.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {

    private final Recipes recipes;

    public RecipeAdapter(Recipes recipes) {
        this.recipes = recipes;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        final Results result = recipes.getResults().get(position);
        String title = result.getTitle().replace(Constants.AMP, Constants.AMPERSAND);
        holder.titleTextView.setText(title);
    }

    @Override
    public int getItemCount() {
        if (recipes == null || recipes.getResults() == null) {
            return 0;
        } else {
            return recipes.getResults().size();
        }
    }
}
