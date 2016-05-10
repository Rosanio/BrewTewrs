package com.epicodus.brewtewrs.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.brewtewrs.Constants;
import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.model.Brewery;
import com.epicodus.brewtewrs.model.Location;
import com.epicodus.brewtewrs.services.BrewDBService;
import com.epicodus.brewtewrs.services.GeocodingService;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.addressEditText)
    EditText mAddressEditText;
    @Bind(R.id.submitButton)
    Button mSubmitButton;
    @Bind(R.id.textView)
    TextView mTextView;
    public Location mLocation;
    public ArrayList<Brewery> breweries = new ArrayList<>();
    private Firebase mLocationRef;
    private Query mQuery;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mLocationRef = new Firebase(Constants.FIREBASE_URL_LOCATIONS);

        mSubmitButton.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.submitButton) {
            String location = mAddressEditText.getText().toString();
            getLocation(location);
        }
    }

    private void getLocation(String location) {
        final GeocodingService geocodingService = new GeocodingService();
        geocodingService.findLatLng(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mLocation = geocodingService.processResults(response);
                mLocationRef.orderByChild("lat").equalTo(mLocation.getLat()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if(snapshot.getValue() != null) {
                            mEditor.putString("lat" ,mLocation.getLat()).apply();
                            mEditor.putString("lng" ,mLocation.getLng()).apply();
                            Intent intent = new Intent(SearchActivity.this, BarCrawlActivity.class);
                            startActivity(intent);
                        } else {
                            Firebase newLocationRef = mLocationRef.push();
                            String pushId = newLocationRef.getKey();
                            mLocation.setPushId(pushId);
                            newLocationRef.setValue(mLocation);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError error) {

                    }
                });

//                String lat = latLng.get(0);
//                String lng = latLng.get(1);
//                final BrewDBService brewDBService = new BrewDBService();
//                brewDBService.findBreweries(lat, lng, new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) {
//                        brewDBService.processResults(response);
//                    }
//                });
            }
        });
    }
}
