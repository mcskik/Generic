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

public class MainFragment extends Fragment {

    public static final String BASE_URL_2 = "https://api.learn2crack.com/";
    private View progressOverlay;
    private RecyclerView recyclerView;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setupScreenLayout(view);
        initializeRecyclerView();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle item selection
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

    private void initializeRecyclerView() {
        LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(getActivity());
        linearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManagerVertical);
    }

    private void setRecyclerViewAdapter(String json) {
    }
}
