package com.epicodus.brewtewrs.ui;

//set up fragment view to display both fragments if in landscape mode and navigate to new activity otherwise

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.adapters.BeerListAdapter;
import com.epicodus.brewtewrs.adapters.BreweryListAdapter;
import com.epicodus.brewtewrs.model.Beer;
import com.epicodus.brewtewrs.model.Brewery;
import com.epicodus.brewtewrs.services.BrewDBService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeerListFragment extends Fragment {
    private SharedPreferences mSharedPreferences;
    private String mLat;
    private String mLng;
    public ArrayList<Beer> beers = new ArrayList<>();
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Brewery mBrewery;
    private BeerListAdapter mAdapter;

    public BeerListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_list, container, false);
        ButterKnife.bind(this, view);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mLat = mSharedPreferences.getString("lat", null);
        mLng = mSharedPreferences.getString("lng", null);

        Intent intent = getActivity().getIntent();
        Integer position = intent.getIntExtra("position", 0);
        ArrayList<Brewery> breweries = Parcels.unwrap(intent.getParcelableExtra("breweries"));

        mBrewery = breweries.get(position);
        String breweryId = mBrewery.getBrewId();

        getBeers(breweryId);
        return view;
    }

    public void getBeers(String breweryId) {
        final BrewDBService brewDBService = new BrewDBService();

        brewDBService.findBeers(breweryId, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                beers = brewDBService.processBeerResults(response);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new BeerListAdapter(getActivity(), beers);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}