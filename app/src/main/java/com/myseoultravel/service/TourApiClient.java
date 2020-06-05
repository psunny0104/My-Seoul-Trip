package com.myseoultravel.service;

import android.content.Context;
import android.util.Log;

import com.myseoultravel.model.place.tour.AreaListModel;
import com.myseoultravel.model.place.tour.ComInfoModel;
import com.myseoultravel.model.place.tour.IntroInfoModel;
import com.myseoultravel.model.place.tour.LocalListModelItems;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TourApiClient {

    private TourApiService tourApiService;
    public static String tourBaseUrl = TourApiService.TOUR_BASE_URL;
    private static Context mContext;
    private static Retrofit tourRetrofit;

    private static class SingletonHolder {
        private static TourApiClient INSTANCE = new TourApiClient(mContext);
    }

    public static TourApiClient getInstance(Context context) {
        if (context != null) {
            mContext = context;
        }
        return SingletonHolder.INSTANCE;
    }

    private TourApiClient(Context context) {
        tourRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(tourBaseUrl)
                .build();
    }

    public TourApiClient createBaseApi() {
        tourApiService = create(TourApiService.class);
        return this;
    }

    public  <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return tourRetrofit.create(service);
    }

    /**
     * 1. 위치기반 관광정보 조회 (리스트)
     */
    public void getLocationList(String ServiceKey, String MobileOs, String MobileApp, String _type, int numOfRows, int pageNo, String arrange, int contentTypeId, double mapX, double mapY, int radius,  ApiCallback<LocalListModelItems> callback) {
        tourApiService.getLocationList(ServiceKey, MobileOs, MobileApp, _type, numOfRows, pageNo, arrange, contentTypeId, mapX, mapY, radius).enqueue(new Callback<LocalListModelItems>() {
            @Override
            public void onResponse(Call<LocalListModelItems> call, Response<LocalListModelItems> response) {
                if (response.isSuccessful()) {
                    System.out.println(call.request().url());
                    try {
                        callback.onSuccess(response.code(), response.body());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("myTag", "responseFailure"+call.request().url());
                    callback.onFailure(response.code());
                }
            }
            @Override
            public void onFailure(Call<LocalListModelItems> call, Throwable t) {
                Log.e("myTag", "responseFailure"+call.request().url());
                callback.onError(t);
            }
        });
    }

    /**
     * 2. 지역기반 관광정보 조회 (리스트)
     */
    public void getAreaList(String ServiceKey, String MobileOs, String MobileApp, String _type, int numOfRows, int pageNo, String arrange, int contentTypeId, int areaCode, String sigunguCode,  ApiCallback<AreaListModel> callback) {
        tourApiService.getAreaList(ServiceKey, MobileOs, MobileApp, _type, numOfRows, pageNo, arrange, contentTypeId, areaCode,sigunguCode).enqueue(new Callback<AreaListModel>() {
            @Override
            public void onResponse(Call<AreaListModel> call, Response<AreaListModel> response) {
                if (response.isSuccessful()) {
                    System.out.println(call.request().url());
                    try {
                        callback.onSuccess(response.code(), response.body());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("myTag", "responseFailure"+call.request().url());
                    callback.onFailure(response.code());
                }
            }
            @Override
            public void onFailure(Call<AreaListModel> call, Throwable t) {
                Log.e("myTag", "responseFailure"+call.request().url());
                callback.onError(t);
            }
        });
    }


    /**
     * 4-1. 상세정보1_공통정보 조회
     */
    public void getCommonInfo(String ServiceKey, String MobileOs, String MobileApp, String _type, int contentId, String defaultYN, String firstImageYN, String areaCodeYN, String catCodeYN, String addrInfoYN, String mapInfoYN, String overviewYN, ApiCallback<ComInfoModel> callback) {
        tourApiService.getCommonInfo(ServiceKey, MobileOs, MobileApp, _type, contentId, defaultYN, firstImageYN, areaCodeYN, catCodeYN, addrInfoYN, mapInfoYN, overviewYN).enqueue(new Callback<ComInfoModel>() {
            @Override
            public void onResponse(Call<ComInfoModel> call, Response<ComInfoModel> response) {
                if (response.isSuccessful()) {
                    System.out.println(call.request().url());
                    try {
                        callback.onSuccess(response.code(), response.body());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("myTag", "responseFailure"+call.request().url());
                    callback.onFailure(response.code());
                }
            }
            @Override
            public void onFailure(Call<ComInfoModel> call, Throwable t) {
                Log.e("myTag", "responseFailure"+call.request().url());
                callback.onError(t);
            }
        });
    }

    /**
     * 4-2. 상세정보2_소개정보 조회
     */
    public void getIntroInfo(String ServiceKey, String MobileOs, String MobileApp, String _type, int contentId, int contentTypeId, String introYN, ApiCallback<IntroInfoModel> callback) {
        tourApiService.getIntroInfo(ServiceKey, MobileOs, MobileApp, _type, contentId, contentTypeId, introYN).enqueue(new Callback<IntroInfoModel>() {
            @Override
            public void onResponse(Call<IntroInfoModel> call, Response<IntroInfoModel> response) {
                if (response.isSuccessful()) {
                    System.out.println(call.request().url());
                    try {
                        callback.onSuccess(response.code(), response.body());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e("myTag", "responseFailure"+call.request().url());
                    callback.onFailure(response.code());
                }
            }
            @Override
            public void onFailure(Call<IntroInfoModel> call, Throwable t) {
                Log.e("myTag", "responseFailure"+call.request().url());
                callback.onError(t);
            }
        });
    }

}

