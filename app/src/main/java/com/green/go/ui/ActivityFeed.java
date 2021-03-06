package com.green.go.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.green.go.enums.VIEW_MODE_DENUNCIA;
import com.green.go.gogreen.R;
import com.green.go.interfaces.FeedInteractionListener;
import com.green.go.util.Util;

import java.io.InputStream;

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
            Intent intent = new Intent(this, ActivityDenunciar.class);
            intent.putExtra(Util.ARG_MODO, Util.ARG_MODO_CRIAR);
            startActivity(intent);
        }
    }
}
