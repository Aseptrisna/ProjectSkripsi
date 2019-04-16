package com.khilman.www.aplikasiportalberita.models;

import com.google.gson.annotations.SerializedName;


public class LocationModel {
    @SerializedName("nama_lokasi")
    private String imageLocationName;
    @SerializedName("latitude")
    private String latutide;
    @SerializedName("longitude")
    private String longitude;

    public LocationModel(String imageLocationName, String latutide, String longitude) {
        this.imageLocationName = imageLocationName;
        this.latutide = latutide;
        this.longitude = longitude;
    }

    public LocationModel() {
    }

    public String getImageLocationName() {
        return imageLocationName;
    }

    public void setImageLocationName(String imageLocationName) {
        this.imageLocationName = imageLocationName;
    }

    public String getLatutide() {
        return latutide;
    }

    public void setLatutide(String latutide) {
        this.latutide = latutide;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
