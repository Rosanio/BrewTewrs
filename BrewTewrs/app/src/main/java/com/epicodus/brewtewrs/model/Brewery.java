package com.epicodus.brewtewrs.model;

import org.parceler.Parcel;

/**
 * Created by Guest on 5/10/16.
 */
@Parcel
public class Brewery {
    private String name;
    private String brewId;
    private String description;
    private String isOrganic;
    private String websiteUrl;
    private String lat;
    private String lng;
    private String pushId;

    public Brewery(){}

    public Brewery(String name, String brewId, String isOrganic, String lat, String lng) {
        this.lng = lng;
        this.name = name;
        this.brewId = brewId;
        this.isOrganic = isOrganic;
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public String getBrewId() {
        return brewId;
    }

    public String getDescription() {
        return description;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
