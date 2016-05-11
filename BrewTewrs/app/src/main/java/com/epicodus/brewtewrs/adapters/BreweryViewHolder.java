package com.epicodus.brewtewrs.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.model.Brewery;
import com.epicodus.brewtewrs.ui.BeerListActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/11/16.
 */
public class BreweryViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.textView)
    TextView mTextView;
    private Context mContext;
    private Integer mPosition;
    private ArrayList<Brewery> mBreweries;


    public BreweryViewHolder(View itemView, ArrayList<Brewery> breweries) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mBreweries = breweries;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, BeerListActivity.class);
                intent.putExtra("position", mPosition);
                intent.putExtra("breweries", Parcels.wrap(mBreweries));
                mContext.startActivity(intent);
            }
        });
    }

    public void bindBrewery(Brewery brewery) {
        mTextView.setText(brewery.getName());
    }
}
