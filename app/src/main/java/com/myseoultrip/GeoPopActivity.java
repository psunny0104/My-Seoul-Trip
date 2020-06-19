package com.myseoultrip;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.myseoultrip.model.place.geocoding.GeocodingItem;
import com.myseoultrip.service.ApiCallback;
import com.myseoultrip.service.GoogleGeoClient;
import com.myseoultrip.service.GoogleGeoService;

import org.json.JSONException;

public class GeoPopActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleGeoClient googleGeoClient;
    GoogleMap mMap;
    String targetAdd = "";
    Double targetLat = 0.0;
    Double targetLng = 0.0;
    int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_geo_pop);

        EditText editText = (EditText) findViewById(R.id.geo_input);
        Button searchBtn = (Button) findViewById(R.id.geo_search_button);
        Button okBtn = (Button) findViewById(R.id.geo_ok_btn);
        TextView placeAdd  = (TextView) findViewById(R.id.geo_add);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.geo_map);
        mapFragment.getMapAsync(this);
        googleGeoClient = GoogleGeoClient.getInstance(this).createBaseApi();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGeoInfo(editText, placeAdd);
            }
        });

        Intent rcvdIntent = getIntent();
        pos = rcvdIntent.getIntExtra("pos",-1);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
                intent.putExtra("targetAdd", targetAdd);
                intent.putExtra("targetLng", targetLng);
                intent.putExtra("targetLat", targetLat);
                intent.putExtra("pos",pos);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        setToolbar();
    }

    private void setToolbar() {
        Toolbar geoToolbar = (Toolbar) findViewById(R.id.geo_toolbar);
        setSupportActionBar(geoToolbar);
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

    public void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alert");
        builder.setMessage("Results do not exist. Please check your search terms.");
        builder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    public void getGeoInfo(EditText editText, TextView placeAdd) {
        googleGeoClient.getGeocoding(GoogleGeoService.KEY, editText.getText().toString(), new ApiCallback<GeocodingItem>() {
            @Override
            public void onError(Throwable t) {
                Log.e("myTag", t.toString());
            }

            @Override
            public void onSuccess(int code, GeocodingItem receivedData) throws JSONException {
                GeocodingItem geocodingItem = (GeocodingItem) receivedData;
                if(geocodingItem.getStatus().equals("ZERO_RESULTS")){
                    dialogShow();
                    return;
                }

                placeAdd.setText(geocodingItem.getResults().get(0).getFormattedAddress());
                targetAdd = geocodingItem.getResults().get(0).getFormattedAddress();
                targetLat = geocodingItem.getResults().get(0).getGeometry().getLocation().getLat();
                targetLng = geocodingItem.getResults().get(0).getGeometry().getLocation().getLng();

                LatLng target = new LatLng(geocodingItem.getResults().get(0).getGeometry().getLocation().getLat(),geocodingItem.getResults().get(0).getGeometry().getLocation().getLng());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(target)
                        .title("The searched place was marked ");
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(target));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }

            @Override
            public void onFailure(int code) {
                Log.e("myTag", "Failure"+code);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }
}
