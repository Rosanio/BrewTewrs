package com.epicodus.brewtewrs.model;

import org.parceler.Parcel;

/**
 * Created by Guest on 5/11/16.
 */
@Parcel
public class Beer {

    private String name;
    private String abv;
    private String beerId;
    private String isOrganic;
    private String style;
    private String pushId;

    public Beer() {}

    public Beer(String name, String beerId, String isOrganic, String style) {
        this.name = name;
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

    public String getAbv() {
        return abv;
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

    public void setAbv(String abv) {
        this.abv = abv;
    }
}
