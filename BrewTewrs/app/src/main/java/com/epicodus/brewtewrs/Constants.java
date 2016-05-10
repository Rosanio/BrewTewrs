package com.epicodus.brewtewrs;

/**
 * Created by Guest on 5/10/16.
 */
public class Constants {
    public static final String BREW_DB_KEY = BuildConfig.BREW_DB_KEY;

    public static final String BREW_DB_BASE_URL = "http://api.brewerydb.com/v2/";
    public static final String SEARCH_QUERY_PARAMETER = "search";

    public static final String GEOCODING_API_KEY = BuildConfig.GEOCODING_API_KEY;
    public static final String GEOCODING_ROOT_URL = "https://maps.googleapis.com/maps/api/geocode/json";

    public static final String FIREBASE_ROOT_URL = BuildConfig.FIREBASE_ROOT_URL;
    public static final String FIREBASE_LOCATION_USERS = "users";
    public static final String FIREBASE_PROPERTY_EMAIL = "email";
    public static final String KEY_UID = "UID";
    public static final String FIREBASE_URL_USERS = FIREBASE_ROOT_URL + "/" + FIREBASE_LOCATION_USERS;
}
