package com.green.go.dataparser;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.green.go.adapters.AdapterFeeds;
import com.green.go.models.Denuncia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by navega on 11/26/16.
 */

public class DenunciaDataParser extends AsyncTask<JSONObject, ArrayList<Denuncia>, ArrayList<Denuncia>> {

    private RecyclerView mRecycler;
    private Context mContext;



    public DenunciaDataParser(Context mContext, RecyclerView mRecycler) {
        this.mRecycler = mRecycler;
        this.mContext = mContext;
    }


    @Override
    protected ArrayList<Denuncia> doInBackground(JSONObject... strings) {
        ArrayList<Denuncia> denunciaArrayList = new ArrayList<>();

        try {
            JSONObject object = strings[0];
            JSONArray publicacoes = object.getJSONArray("publicacaos");

            for (int i = 0; i < publicacoes.length(); ++i) {
                JSONObject publicacao = publicacoes.getJSONObject(i);

                Denuncia denuncia = new Denuncia();
                denuncia.setmTitulo(publicacao.getString("titulo"));
                denuncia.setmDescricao(publicacao.getString("descricao"));
                denunciaArrayList.add(denuncia);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return denunciaArrayList;
    }


    @Override
    protected void onPostExecute(ArrayList<Denuncia> denuncias) {
        super.onPostExecute(denuncias);

        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setAdapter(new AdapterFeeds(mContext, denuncias));
    }
}
