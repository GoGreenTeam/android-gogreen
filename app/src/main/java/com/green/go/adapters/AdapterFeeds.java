package com.green.go.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.green.go.gogreen.R;

import java.util.ArrayList;

/**
 * Created by navega on 11/26/16.
 */

public class AdapterFeeds extends RecyclerView.Adapter<AdapterFeeds.ViewHolder> {

    //data
    private ArrayList<String> mDataSet;

    public AdapterFeeds(ArrayList<String> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_feeds, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.feeds_cardview);
            mTitle = (TextView) itemView.findViewById(R.id.feeds_title);
        }
    }

}
