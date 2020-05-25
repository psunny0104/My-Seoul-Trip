package com.myseoultravel.service;

import com.myseoultravel.model.place.detail.DetailItem;
import com.myseoultravel.model.place.nearby.NearbyPlaceItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlaceService {
    final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    final String KEY = "AIzaSyAIzAReIEnmcFpViaSTaMvXLiW4FExvrVY";
    final int RADIUS = 3000;
    final String LANGUAGE = "en";

    @GET("nearbysearch/json")
    Call<NearbyPlaceItem> getSearchNearbyPlace(@Query("key") String key,
                                               @Query("type") String type,
                                               @Query("radius") int radius,
                                               @Query("location") String location,
                                               @Query("language") String language);

    @GET("details/json")
    Call<DetailItem> getDetailPlace(@Query("key") String key,
                                    @Query("place_id") String placeId);
}
