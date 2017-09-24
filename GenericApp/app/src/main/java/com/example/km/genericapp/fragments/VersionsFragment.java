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
import com.example.km.genericapp.adapters.AndroidVersionAdapter;
import com.example.km.genericapp.constants.Urls;
import com.example.km.genericapp.models.versions.AndroidVersion;
import com.example.km.genericapp.network.AndroidVersionRequestInterface;
import com.example.km.genericapp.utilities.SnackbarHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class VersionsFragment extends Fragment {

    private View progressOverlay;
    private RecyclerView recyclerView;
    private CompositeDisposable disposables;
    private AndroidVersionAdapter adapter;
    private ArrayList<AndroidVersion> androidArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_versions, container, false);
        setupScreenLayout(view);
        disposables = new CompositeDisposable();
        initializeRecyclerView();
        loadVersions();
        return view;
    }

    private void initializeRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Load all Android versions.
     */
    private void loadVersions() {
        showProgressIndicator();
        AndroidVersionRequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(Urls.ANDROID_VERSIONS_API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(AndroidVersionRequestInterface.class);

        Observable<List<AndroidVersion>> resultObservable = requestInterface.register();
        disposables.add(resultObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<AndroidVersion>>() {
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
                    public void onNext(List<AndroidVersion> androidList) {
                        handleResponse(androidList);
                    }
                }));
    }

    private void handleResponse(List<AndroidVersion> androidList) {
        androidArrayList = new ArrayList<>(androidList);
        adapter = new AndroidVersionAdapter(androidArrayList);
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
