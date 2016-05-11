package com.epicodus.brewtewrs.ui;


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
import com.epicodus.brewtewrs.adapters.BreweryListAdapter;
import com.epicodus.brewtewrs.model.Brewery;
import com.epicodus.brewtewrs.services.BrewDBService;

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
public class BreweryListFragment extends Fragment {
    private SharedPreferences mSharedPreferences;
    private String mLat;
    private String mLng;
    public ArrayList<Brewery> mBreweries = new ArrayList<>();
    private BreweryListAdapter mAdapter;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;


    public BreweryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_brewery_list, container, false);
        ButterKnife.bind(this, view);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mLat = mSharedPreferences.getString("lat", null);
        mLng = mSharedPreferences.getString("lng", null);
        Log.v("lat", mLat + "");
        Log.v("lng", mLng + "");

        getBreweries(mLat, mLng);

        return view;
    }

    private void getBreweries(String lat, String lng) {
        final BrewDBService brewDBService = new BrewDBService();

        brewDBService.findBreweries(lat, lng, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mBreweries = brewDBService.processResults(response);

                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        mAdapter = new BreweryListAdapter(getActivity(), mBreweries);
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
