package com.epicodus.brewtewrs.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.brewtewrs.R;
import com.epicodus.brewtewrs.model.Beer;
import com.epicodus.brewtewrs.ui.BeerListActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 5/11/16.
 */
public class BeerViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.textView)
    TextView mTextView;
    private Context mContext;
    private Integer mPosition;
    private ArrayList<Beer> mBeers;


    public BeerViewHolder(View itemView, ArrayList<Beer> breweries) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mBeers = breweries;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, BeerListActivity.class);
                intent.putExtra("position", mPosition);
                intent.putExtra("breweries", Parcels.wrap(mBeers));
                mContext.startActivity(intent);
            }
        });
    }

    public void bindBeer(Beer brewery) {
        mTextView.setText(brewery.getName());
    }
}