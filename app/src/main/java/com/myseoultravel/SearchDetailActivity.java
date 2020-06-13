package com.myseoultravel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myseoultravel.adapter.PoiItem;
import com.myseoultravel.model.place.detail.DetailItem;
import com.myseoultravel.model.place.tour.CategoryCodeModel;
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

    int contentIdb = 0;
    int contentTypeIdb = 0;
    Double mapX = 0.0;
    Double mapY = 0.0;
    String address = "";
    String title = "";
    String image = "";
    CategoryCodeModel categoryCodeModel = new CategoryCodeModel();
    String cat1 = "";
    String cat2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        setToolbar();
        categoryCodeModel.init();

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

        Button selectBtn = (Button) findViewById(R.id.detail_select);
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(getApplicationContext(),CourseActivity.class);
                PoiItem poiItem = new PoiItem(intent.getIntExtra("poiCount",0),contentIdb,contentTypeIdb,mapX,mapY,address,title,image);
                Log.i("myTag","Detail: "+poiItem.getPoiTitle());
                newIntent.putExtra("poiItem",poiItem);
                newIntent.putExtra("pos",intent.getIntExtra("pos",0));
                newIntent.putExtra("poiCount",intent.getIntExtra("poiCount",0));
                newIntent.putExtra("courseId",intent.getStringExtra("courseId"));
                newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                setResult(100, newIntent);
                finish();
            }
        });
    }

    private void setToolbar() {
        Toolbar scheduleBar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(scheduleBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_keyboard_backspace_white_48dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                // TODO : process the click event for action_search item.
                onBackPressed();
                return true ;
            // ...
            // ...
            default :
                return super.onOptionsItemSelected(item) ;
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

                contentIdb = comInfoModel.getResponse().getBody().getItems().getItem().getContentid();
                contentTypeIdb = comInfoModel.getResponse().getBody().getItems().getItem().getContenttypeid();
                mapX = comInfoModel.getResponse().getBody().getItems().getItem().getMapx();
                mapY = comInfoModel.getResponse().getBody().getItems().getItem().getMapy();
                address = comInfoModel.getResponse().getBody().getItems().getItem().getAddr1();
                title = comInfoModel.getResponse().getBody().getItems().getItem().getTitle();
                image = comInfoModel.getResponse().getBody().getItems().getItem().getFirstimage();
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
