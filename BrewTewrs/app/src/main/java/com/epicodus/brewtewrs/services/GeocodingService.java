package com.epicodus.brewtewrs.services;

import com.epicodus.brewtewrs.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Connection;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 5/10/16.
 */
public class GeocodingService {
    public static void findLatLng(String location, Callback callback) {
        String API_KEY = Constants.GEOCODING_API_KEY;

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.GEOCODING_ROOT_URL).newBuilder();
        urlBuilder.addQueryParameter("address", location);
        urlBuilder.addQueryParameter("key", API_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<String> processResults(Response response) {
        ArrayList<String> latLng = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if(response.isSuccessful()) {
                JSONObject geocodingJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = geocodingJSON.getJSONArray("results");
                JSONObject locationJSON = resultsJSON.getJSONObject(0);
                JSONObject geometryJSON = locationJSON.getJSONObject("geometry");
                JSONObject actualLocationJSON = geometryJSON.getJSONObject("location");
                String lat = actualLocationJSON.getString("lat");
                String lng = actualLocationJSON.getString("lng");
                latLng.add(lat);
                latLng.add(lng);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return latLng;
    }

}
