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
import com.myseoultravel.service.GoogleCallback;
import com.myseoultravel.service.GooglePlaceClient;
import com.myseoultravel.service.GooglePlaceService;

import org.w3c.dom.Text;

public class SearchDetailActivity extends AppCompatActivity {
    GooglePlaceClient googlePlaceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);

        googlePlaceClient = GooglePlaceClient.getInstance(this).createBaseApi();
        Intent intent = getIntent();
        searchDetailPlace(intent.getStringExtra("place_id"));
    }

    public void searchDetailPlace(String placeId){
        googlePlaceClient.getDetailPlace(GooglePlaceService.KEY, placeId, new GoogleCallback() {
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
