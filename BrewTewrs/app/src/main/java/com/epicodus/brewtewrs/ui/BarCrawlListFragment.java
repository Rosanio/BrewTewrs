package com.epicodus.brewtewrs.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.epicodus.brewtewrs.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarCrawlListFragment extends Fragment implements View.OnClickListener{
    @Bind(R.id.newBreweryCrawlButton) Button mNewBreweryCrawlButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public BarCrawlListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bar_crawl_list, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this, view);
        mNewBreweryCrawlButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Log.v("hello", "yooooo");
        if (v.getId() == R.id.newBreweryCrawlButton) {

            Intent intent = new Intent(getActivity(), CreateBarCrawlActivity.class);
            startActivity(intent);
        }
    }
}
