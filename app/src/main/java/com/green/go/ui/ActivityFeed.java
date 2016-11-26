package com.green.go.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.green.go.gogreen.R;
import com.green.go.interfaces.FeedInteractionListener;

public class ActivityFeed extends ActivityBase implements FeedInteractionListener, View.OnClickListener {

    //layout
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        mFab = (FloatingActionButton) findViewById(R.id.feeds_fab);
        mFab.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.feeds_fab) {
            startActivity(new Intent(this, ActivityDenunciar.class));
        }
    }
}
