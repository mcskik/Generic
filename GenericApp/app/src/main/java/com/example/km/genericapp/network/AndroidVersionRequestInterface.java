package com.example.km.genericapp.network;

import com.example.km.genericapp.models.versions.AndroidVersion;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AndroidVersionRequestInterface {

    @GET("android/jsonarray/")
    Observable<List<AndroidVersion>> register();
}
