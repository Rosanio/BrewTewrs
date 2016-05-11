package com.epicodus.brewtewrs.model;

import org.parceler.Parcel;

/**
 * Created by Guest on 5/11/16.
 */
@Parcel
public class Beer {

    private String name;
    private String description;
    private String abv;
    private String availability;
    private String beerId;
    private String isOrganic;
    private String style;
    private String pushId;

    public Beer() {}

    public Beer(String pushId, String name, String description, String abv, String availability, String beerId, String isOrganic, String style) {
        this.pushId = pushId;
        this.name = name;
        this.description = description;
        this.abv = abv;
        this.availability = availability;
        this.beerId = beerId;
        this.isOrganic = isOrganic;
        this.style = style;
    }

    public String getPushId() {
        return pushId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAbv() {
        return abv;
    }

    public String getAvailability() {
        return availability;
    }

    public String getBeerId() {
        return beerId;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public String getStyle() {
        return style;
    }
}
