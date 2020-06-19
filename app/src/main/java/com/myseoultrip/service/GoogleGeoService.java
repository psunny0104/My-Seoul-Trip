package com.myseoultrip.service;

import com.myseoultrip.model.place.geocoding.GeocodingItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleGeoService {
    final String BASE_URL = "https://maps.googleapis.com/maps/api/geocode/";
    final String KEY = "AIzaSyAIzAReIEnmcFpViaSTaMvXLiW4FExvrVY";
    final String LANGUAGE = "en";

    @GET("json")
    Call<GeocodingItem> getSearchNearbyPlace(@Query("key") String key,
                                             @Query("address") String type);
}
