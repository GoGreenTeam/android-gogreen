package com.green.go.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.green.go.gogreen.R;
import com.green.go.models.Denuncia;
import com.green.go.ui.ActivityDenunciar;

import java.util.ArrayList;

/**
 * Created by navega on 11/26/16.
 */

public class AdapterFeeds extends RecyclerView.Adapter<AdapterFeeds.ViewHolder> {

    //data
    private ArrayList<Denuncia> mDataSet;
    private Context mContext;

    public AdapterFeeds(Context context, ArrayList<Denuncia> mDataSet) {
        this.mDataSet = mDataSet;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_feeds, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTitle.setText(mDataSet.get(position).getmTitulo());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivityDenunciar.class);
                intent.putExtra("DENUNCIA", mDataSet.get(position));
                mContext.startActivity(intent);
            }
        });
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
