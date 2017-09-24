package com.example.km.genericapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.km.genericapp.R;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class SettingsFragment extends Fragment {

    private PublishSubject<Boolean> postsSubject;
    private PublishSubject<Boolean> recipesSubject;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        Button homeButton = (Button) rootView.findViewById(R.id.btnPosts);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postsSubject.onNext(true);
            }
        });
        Button settingsButton = (Button) rootView.findViewById(R.id.btnRecipes);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recipesSubject.onNext(true);
            }
        });
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Observable<Boolean> launchPosts() {
        if (postsSubject == null) {
            postsSubject = PublishSubject.create();
        }
        return postsSubject;
    }

    public Observable<Boolean> launchRecipes() {
        if (recipesSubject == null) {
            recipesSubject = PublishSubject.create();
        }
        return recipesSubject;
    }
}
