package com.epicodus.brewtewrs.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.model.Brewery;

import org.parceler.Parcels;

import java.util.ArrayList;

public class BeerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_list);

        Intent intent = getIntent();

    }
}
