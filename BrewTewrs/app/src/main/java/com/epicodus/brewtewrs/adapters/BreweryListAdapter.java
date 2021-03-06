package com.epicodus.brewtewrs.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.model.Brewery;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/11/16.
 */
public class BreweryListAdapter extends RecyclerView.Adapter<BreweryViewHolder> {
    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private Context mContext;

    public BreweryListAdapter(Context context, ArrayList<Brewery> breweries) {
        mContext = context;
        mBreweries = breweries;
    }

    @Override
    public BreweryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_list_item, parent, false);
        BreweryViewHolder viewHolder = new BreweryViewHolder(view, mBreweries);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BreweryViewHolder holder, int position) {
        holder.bindBrewery(mBreweries.get(position));
    }

    @Override
    public int getItemCount() {
        return mBreweries.size();
    }
}
