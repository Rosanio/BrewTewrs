package com.epicodus.brewtewrs.model;

/**
 * Created by Guest on 5/10/16.
 */
public class Location {

    private String lat;
    private String lng;
    private String pushId;

    public Location() {}

    public Location(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public String getLng() {
        return lng;
    }

    public String getLat() {
        return lat;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
