package com.epicodus.brewtewrs.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.model.Brewery;
import com.epicodus.brewtewrs.services.BrewDBService;
import com.epicodus.brewtewrs.services.GeocodingService;

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
    public ArrayList<String> latLng = new ArrayList<>();
    public ArrayList<Brewery> breweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        mSubmitButton.setOnClickListener(this);
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
                latLng = geocodingService.processResults(response);

                String lat = latLng.get(0);
                String lng = latLng.get(1);
                final BrewDBService brewDBService = new BrewDBService();
                brewDBService.findBreweries(lat, lng, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) {
                        brewDBService.processResults(response);
                    }
                });
            }
        });
    }
}
