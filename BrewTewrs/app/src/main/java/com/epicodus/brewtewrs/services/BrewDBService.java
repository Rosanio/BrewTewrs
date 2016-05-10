package com.epicodus.brewtewrs.services;

import android.util.Log;

import com.epicodus.brewtewrs.Constants;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 5/10/16.
 */
public class BrewDBService {
    public static void findBreweries(String lat, String lng, Callback callback) {
        String API_KEY = Constants.BREW_DB_KEY;

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREW_DB_BASE_URL).newBuilder();
        urlBuilder.addPathSegment("search");
        urlBuilder.addPathSegment("geo");
        urlBuilder.addPathSegment("point");
        urlBuilder.addQueryParameter("lat", lat);
        urlBuilder.addQueryParameter("lng", lng);
        urlBuilder.addQueryParameter("key", API_KEY);
        String url = urlBuilder.build().toString();
        Log.d("data", url);

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public void processResults(Response response) {
        try {
            String jsonData = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
    }
}
