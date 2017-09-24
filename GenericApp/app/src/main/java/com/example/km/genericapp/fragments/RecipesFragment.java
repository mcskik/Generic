package com.example.km.genericapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.km.genericapp.R;
import com.example.km.genericapp.adapters.RecipeAdapter;
import com.example.km.genericapp.constants.Constants;
import com.example.km.genericapp.models.recipes.Recipes;
import com.example.km.genericapp.network.RecipeApiService;
import com.example.km.genericapp.utilities.SnackbarHelper;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RecipesFragment extends Fragment {

    private View progressOverlay;
    private RecyclerView recyclerView;
    private CompositeDisposable disposables;
    private RecipeAdapter adapter;
    protected EditText searchText;
    private ProgressBar searchProgressBar;
    private ImageButton searchClearButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        setupScreenLayout(view);
        disposables = new CompositeDisposable();
        initializeRecyclerView();
        loadRecipes(Constants.EMPTY_STRING);
        return view;
    }

    private void initializeRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadRecipes(String query) {
        showProgressIndicator();
        disposables.add(RecipeApiService.getRecipes(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Recipes>() {
                    @Override
                    public void onComplete() {
                        hideProgressIndicator();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        handleError(throwable);
                    }

                    @Override
                    public void onNext(Recipes recipes) {
                        handleResponse(recipes);
                    }
                }));
    }

    private void handleResponse(Recipes recipes) {
        adapter = new RecipeAdapter(recipes);
        recyclerView.setAdapter(adapter);
    }

    private void handleError(Throwable error) {
        SnackbarHelper.showSnackbar(getActivity(), recyclerView, getActivity().getString(R.string.api_call_error) + error.getLocalizedMessage());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
            case R.id.action_home:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    public void showProgressIndicator() {
        searchProgressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressIndicator() {
        searchProgressBar.setVisibility(View.GONE);
    }

    private void setupScreenLayout(View view) {
        progressOverlay = view.findViewById(R.id.progressOverlay);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        searchProgressBar = (ProgressBar) view.findViewById(R.id.searchProgressBar);
        searchClearButton = (ImageButton) view.findViewById(R.id.searchClearButton);
        searchText = (EditText) view.findViewById(R.id.searchEditText);
        searchText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                handleSearchTextUpdate();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        searchClearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchText.setText(Constants.EMPTY_STRING);
            }
        });

        //noinspection deprecation
        searchProgressBar.getIndeterminateDrawable().setColorFilter(
                getResources().getColor(R.color.colorAccent),
                android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    private void handleSearchTextUpdate() {
        final String query = searchText.getText().toString();
        if (TextUtils.isEmpty(query)) {
            searchClearButton.setVisibility(View.GONE);
        } else {
            searchClearButton.setVisibility(View.VISIBLE);
        }
        loadRecipes(query);
    }
}
