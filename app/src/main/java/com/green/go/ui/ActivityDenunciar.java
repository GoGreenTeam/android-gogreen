package com.green.go.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.green.go.enums.REQUEST_TYPE;
import com.green.go.enums.VIEW_MODE_DENUNCIA;
import com.green.go.gogreen.R;
import com.green.go.interfaces.DenunciarInterfaceListener;
import com.green.go.models.Denuncia;
import com.green.go.server.JsonBuilder;
import com.green.go.server.Request;
import com.green.go.util.Util;

import org.json.JSONObject;

public class ActivityDenunciar extends AppCompatActivity implements DenunciarInterfaceListener {

    private static final String ARG_DENUNCIA = "DENUNCIA";
    private String MODO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denunciar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        MODO = intent.getStringExtra(Util.ARG_MODO);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(this);

        Denuncia denuncia = intent.getParcelableExtra(ARG_DENUNCIA);
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.denunciar_framelayout, ActivityDenunciarFragment.newInstance(denuncia, MODO));
        transaction.commit();
    }

    @Override
    public void sendDenuncia(String titulo, String description, String concelho, String longitude, String latitude) {
        String json = JsonBuilder.BUILD_PUBLICATION(titulo, description, concelho, latitude, longitude);
        new Request(REQUEST_TYPE.POST, this, json) {
            @Override
            public void onPostExecute(JSONObject data) {
                super.onPostExecute(data);
            }
        }.execute(Util.REQUEST_CREATE_PUBLICACOES);
    }
}
