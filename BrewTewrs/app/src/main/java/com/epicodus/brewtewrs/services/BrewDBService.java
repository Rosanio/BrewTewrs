package com.epicodus.brewtewrs.services;

import android.util.Log;

import com.epicodus.brewtewrs.Constants;
import com.epicodus.brewtewrs.model.Brewery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

    public ArrayList<Brewery> processResults(Response response) {
        ArrayList<Brewery> breweries = new ArrayList<>();
        try {

            String jsonData = response.body().string();
            JSONObject brewDBObject = new JSONObject(jsonData);
            JSONArray brewDataJSON = brewDBObject.getJSONArray("data");
            for (int i = 0; i < brewDataJSON.length(); i++) {
                JSONObject locationJSON = brewDataJSON.getJSONObject(i);
                JSONObject breweryJSON = locationJSON.getJSONObject("brewery");
                String lat = locationJSON.getString("latitude");
                String lng = locationJSON.getString("longitude");
                String name = breweryJSON.getString("name");
                String brewId = breweryJSON.getString("id");
                String isOrganic = breweryJSON.getString("isOrganic");

                Brewery brewery = new Brewery(name, brewId, isOrganic, lat, lng);

                if(breweryJSON.has("description")) {
                    String description = breweryJSON.getString("description");
                    brewery.setDescription(description);
                }

                if(breweryJSON.has("website")) {
                    String websiteUrl = breweryJSON.getString("website");
                    brewery.setWebsiteUrl(websiteUrl);
                }
                breweries.add(brewery);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return breweries;
    }
}
