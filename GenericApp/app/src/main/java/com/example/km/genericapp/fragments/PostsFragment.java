package com.example.km.genericapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.km.genericapp.R;
import com.example.km.genericapp.adapters.PostCardAdapter;
import com.example.km.genericapp.models.posts.BlogItem;
import com.example.km.genericapp.network.PostalApiService;
import com.example.km.genericapp.utilities.SnackbarHelper;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class PostsFragment extends Fragment {

    private View progressOverlay;
    private RecyclerView recyclerView;
    private CompositeDisposable disposables;
    private PostCardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_posts, container, false);
        setupScreenLayout(view);
        disposables = new CompositeDisposable();
        initializeRecyclerView();
        loadBlogItems();
        return view;
    }

    private void initializeRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void loadBlogItems() {
        showProgressIndicator();
        disposables.add(PostalApiService.getBlogItems()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ArrayList<BlogItem>>() {
                    @Override
                    public void onComplete() {
                        hideProgressIndicator();
                        SnackbarHelper.showSnackbar(getActivity(), recyclerView, getActivity().getString(R.string.api_call_complete));
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        handleError(throwable);
                    }

                    @Override
                    public void onNext(ArrayList<BlogItem> blogItems) {
                        handleBlogDataResponse(blogItems);
                    }
                }));
    }

    private void handleBlogDataResponse(ArrayList<BlogItem> blogItems) {
        ArrayList<BlogItem> items = blogItems;
        adapter = new PostCardAdapter(blogItems);
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
        progressOverlay.setVisibility(View.VISIBLE);
    }

    public void hideProgressIndicator() {
        progressOverlay.setVisibility(View.GONE);
    }

    private void setupScreenLayout(View view) {
        progressOverlay = view.findViewById(R.id.progressOverlay);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    }
}
