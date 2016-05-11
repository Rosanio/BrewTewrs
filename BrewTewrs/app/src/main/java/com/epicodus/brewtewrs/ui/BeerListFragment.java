package com.epicodus.brewtewrs.ui;

//set up fragment view to display both fragments if in landscape mode and navigate to new activity otherwise

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.model.Beer;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

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

//        getBeers();
        return view;
    }

}
