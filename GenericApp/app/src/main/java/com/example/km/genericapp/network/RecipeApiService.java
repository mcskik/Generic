package com.example.km.genericapp.network;

import com.example.km.genericapp.constants.Urls;
import com.example.km.genericapp.models.recipes.Recipes;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Recipe Puppy API service.
 */
public class RecipeApiService {
    private static RecipeApiInterface retrofitService;

    private static RecipeApiInterface getService() {
        if (retrofitService == null) {
            retrofitService = new Retrofit.Builder()
                    .baseUrl(Urls.RECIPE_PUPPY_API_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(RecipeApiInterface.class);
        }
        return retrofitService;
    }

    public static Observable<Recipes> getRecipes(String query) {
        return getService().getRecipes(query);
    }

    public interface RecipeApiInterface {
        @GET("api/")
        Observable<Recipes> getRecipes(@Query("q") String query);
    }
}
