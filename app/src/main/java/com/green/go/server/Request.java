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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.Charset;


public class Request extends AsyncTask<String, Boolean, JSONObject> {

    private static final int HTTP_TIMEOUT = 5000;

    private boolean hasInternet = true;

    public Request(Context context) {

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    protected JSONObject doInBackground(String... urls) {
        //if (Util.hasInternetAccess(this.mContext) == false) {
        //    this.hasInternet = false;
        //    publishProgress(false);
        //}

        String json = null;
        JSONObject jsonObject = null;
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(HTTP_TIMEOUT);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            json = read(rd);
            jsonObject = new JSONObject(json);


        } catch (IOException e) {
            // Writing exception to log
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (isCancelled()) {
            publishProgress(true);
            return null;
        }


        return jsonObject;
    }


    public void onPostExecute(JSONObject data) {
    }


    private String read(BufferedReader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        String temp = "";
        while ((temp = rd.readLine()) != null) {
            sb.append(temp);
        }
        return sb.toString();
    }
}