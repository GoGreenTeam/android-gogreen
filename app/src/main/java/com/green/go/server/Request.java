package com.green.go.server;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Network;
import android.net.http.RequestQueue;
import android.os.AsyncTask;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;


public class Request extends AsyncTask<String, Boolean, String> {

    private boolean hasInternet = true;

    private HttpClient httpclient;

    public Request(Context context) {

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected String doInBackground(String... urls) {
        //if (Util.hasInternetAccess(this.mContext) == false) {
        //    this.hasInternet = false;
        //    publishProgress(false);
        //}

        StringBuffer chaine = new StringBuffer("");
        String line = "";

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = rd.readLine()) != null) {
                chaine.append(line);
            }
        } catch (IOException e) {
            // Writing exception to log
            e.printStackTrace();
        }


        if (isCancelled()) {
            publishProgress(true);
            return null;
        }
        return line;
    }


    public void onPostExecute(String data) {
    }

}