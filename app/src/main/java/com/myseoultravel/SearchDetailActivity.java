package com.myseoultravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myseoultravel.model.place.detail.DetailItem;
import com.myseoultravel.model.place.tour.ComInfoModel;
import com.myseoultravel.model.place.tour.IntroInfoModel;
import com.myseoultravel.service.ApiCallback;
import com.myseoultravel.service.GooglePlaceClient;
import com.myseoultravel.service.GooglePlaceService;
import com.myseoultravel.service.TourApiClient;
import com.myseoultravel.service.TourApiService;

import org.json.JSONException;

public class SearchDetailActivity extends AppCompatActivity {
    GooglePlaceClient googlePlaceClient;
    TourApiClient tourApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        googlePlaceClient = GooglePlaceClient.getInstance(this).createBaseApi();
        tourApiClient = TourApiClient.getInstance(this).createBaseApi();

        Intent intent = getIntent();
        String api = intent.getStringExtra("api");

        if(api.equals("tour")){
            int contentId = intent.getIntExtra("contentId",0);
            int contentTypeId = intent.getIntExtra("contentTypeId", 0);
            Log.i("myTag", api+" "+contentId);
            searchDetailPlaceTour(contentId, contentTypeId);
        }
        else if (api.equals("google")) {
            searchDetailPlace(intent.getStringExtra("place_id"));
        }
    }

    public void searchDetailPlaceTour(int contentId, int contentTypeId){
        tourApiClient.getCommonInfo(TourApiService.SERVICE_KEY, TourApiService.MOBILE_OS, TourApiService.MOBILE_App, TourApiService.MY_TYPE, contentId, "Y", "Y", "Y", "Y", "Y", "Y", "Y", new ApiCallback<ComInfoModel>() {
            @Override
            public void onError(Throwable t) {
                Log.e("myTag", t.toString());
            }

            @Override
            public void onSuccess(int code, ComInfoModel receivedData) throws JSONException {
                ComInfoModel comInfoModel = (ComInfoModel) receivedData;
                TextView textView = (TextView) findViewById(R.id.detail_title);
                textView.setText(comInfoModel.getResponse().getBody().getItems().getItem().getTitle());

                TextView textView1 = (TextView) findViewById(R.id.detail_overview);
                textView1.setText(comInfoModel.getResponse().getBody().getItems().getItem().getOverview());
                ImageView viewThumbnail = (ImageView) findViewById(R.id.detail_image) ;
                String photo = comInfoModel.getResponse().getBody().getItems().getItem().getFirstimage();
                Log.i("myTag", photo);
                getImageByGlide(photo, viewThumbnail);
            }

            @Override
            public void onFailure(int code) {
                Log.e("myTag", "Failure"+code);
            }
        });

        tourApiClient.getIntroInfo(TourApiService.SERVICE_KEY, TourApiService.MOBILE_OS, TourApiService.MOBILE_App, TourApiService.MY_TYPE, contentId, contentTypeId, "Y", new ApiCallback<IntroInfoModel>() {
            @Override
            public void onError(Throwable t) {
                Log.e("myTag", t.toString());
            }

            @Override
            public void onSuccess(int code, IntroInfoModel receivedData) throws JSONException {
                IntroInfoModel introInfoModel = (IntroInfoModel) receivedData;

            }

            @Override
            public void onFailure(int code) {
                Log.e("myTag", "Failure"+code);
            }
        });
    }

    public void searchDetailPlace(String placeId){
        googlePlaceClient.getDetailPlace(GooglePlaceService.KEY, placeId, new ApiCallback() {
            @Override
            public void onError(Throwable t) {
                Log.e("myTag", t.toString());
            }

            @Override
            public void onSuccess(int code, Object receivedData) {
                DetailItem detailItem = (DetailItem) receivedData;
                TextView textView = (TextView) findViewById(R.id.detail_title);
                textView.setText(detailItem.getResult().getName());

                ImageView viewThumbnail = (ImageView) findViewById(R.id.detail_image) ;
                String PHOTOREF = "";
                try{
                    PHOTOREF = detailItem.getResult().getPhotos().get(0).getPhotoReference();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                String photo = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1600&photoreference="+PHOTOREF+"&key="+GooglePlaceService.KEY;
                Log.i("myTag", photo);
                getImageByGlide(photo, viewThumbnail);
            }

            @Override
            public void onFailure(int code) {
                Log.e("myTag", "Failure"+code);
            }
        });
    }

    public void getImageByGlide(String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
//                .placeholder(R.drawable.frame_item)
//                .error(R.drawable.new_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(this)
                .load(url)
                .apply(options)
                .into(imageView);
    }
}
