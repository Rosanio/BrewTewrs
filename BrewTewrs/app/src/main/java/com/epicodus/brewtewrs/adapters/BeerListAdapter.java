package com.epicodus.brewtewrs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.model.Beer;

import java.util.ArrayList;

/**
 * Created by Guest on 5/11/16.
 */
public class BeerListAdapter extends RecyclerView.Adapter<BeerViewHolder> {
    private ArrayList<Beer> mBeers = new ArrayList<>();
    private Context mContext;

    public BeerListAdapter(Context context, ArrayList<Beer> beers) {
        mContext = context;
        mBeers = beers;
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_list_item, parent, false);
        BeerViewHolder viewHolder = new BeerViewHolder(view, mBeers);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        holder.bindBeer(mBeers.get(position));
    }

    @Override
    public int getItemCount() {
        return mBeers.size();
    }
}