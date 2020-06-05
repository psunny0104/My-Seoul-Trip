package com.myseoultravel.service;

import android.content.Context;
import android.util.Log;

import com.myseoultravel.model.place.detail.DetailItem;
import com.myseoultravel.model.place.geocoding.GeocodingItem;
import com.myseoultravel.model.place.nearby.NearbyPlaceItem;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleGeoClient {

    private GoogleGeoService googleGeoService;
    public static String baseUrl = GoogleGeoService.BASE_URL;
    private static Context mContext;
    private static Retrofit retrofit;

    private static class SingletonHolder {
        private static GoogleGeoClient INSTANCE = new GoogleGeoClient(mContext);
    }

    public static GoogleGeoClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }

    private GoogleGeoClient(Context context) {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public GoogleGeoClient createBaseApi() {
        googleGeoService = create(GoogleGeoService.class);
        return this;
    }

    public  <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    public void getGeocoding(String key, String address, final ApiCallback callback){
        googleGeoService.getSearchNearbyPlace(key,address).enqueue(new Callback<GeocodingItem>() {
            @Override
            public void onResponse(Call<GeocodingItem> call, Response<GeocodingItem> response) {
                if (response.isSuccessful()) {
                    Log.i("myTag", "responseSuccess"+call.request().url());
                    try {
                        callback.onSuccess(response.code(), response.body());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Log.i("myTag", "responseFailure"+call.request().url());
                    callback.onFailure(response.code());
                }
            }

            @Override
            public void onFailure(Call<GeocodingItem> call, Throwable t) {
                Log.i("myTag", "responseFailure"+call.request().url());
                callback.onError(t);
            }
        });
    }
}
