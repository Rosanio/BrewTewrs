package com.epicodus.brewtewrs.ui;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.brewtewrs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreweryListFragment extends Fragment {
    private SharedPreferences mSharedPreferences;
    private String mLat;
    private String mLng;


    public BreweryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mLat = mSharedPreferences.getString("lat", null);
        mLng = mSharedPreferences.getString("lng", null);
        Log.v("lat", mLat + "");
        Log.v("lng", mLng + "");
        return inflater.inflate(R.layout.fragment_brewery_list, container, false);
    }
}
