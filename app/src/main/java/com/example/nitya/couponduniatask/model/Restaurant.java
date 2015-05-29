package com.example.nitya.couponduniatask.model;

import android.graphics.Bitmap;

/**
 * Created by Nitya on 23-May-15.
 */
public class Restaurant {
    public int getResponseNumber() {
        return responseNumber;
    }

    public void setResponseNumber(int responseNumber) {
        this.responseNumber = responseNumber;
    }

    public String getSubFranchiseID() {
        return subFranchiseID;
    }

    public void setSubFranchiseID(String subFranchiseID) {
        this.subFranchiseID = subFranchiseID;
    }

    public String getOutletID() {
        return outletID;
    }

    public void setOutletID(String outletID) {
        this.outletID = outletID;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighbourhoodID() {
        return neighbourhoodID;
    }

    public void setNeighbourhoodID(String neighbourhoodID) {
        this.neighbourhoodID = neighbourhoodID;
    }

    public String getCityID() {
        return cityID;
    }

    public void setCityID(String cityID) {
        this.cityID = cityID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getCityRank() {
        return cityRank;
    }

    public void setCityRank(String cityRank) {
        this.cityRank = cityRank;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getOutletURL() {
        return outletURL;
    }

    public void setOutletURL(String outletURL) {
        this.outletURL = outletURL;
    }

    public String getNumCoupons() {
        return numCoupons;
    }

    public void setNumCoupons(String numCoupons) {
        this.numCoupons = numCoupons;
    }

    public String getNeighbourhoodName() {
        return neighbourhoodName;
    }

    public void setNeighbourhoodName(String neighbourhoodName) {
        this.neighbourhoodName = neighbourhoodName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Categories[] getCategories() {
        return categories;
    }

    public void setCategories(Categories[] categories) {
        this.categories = categories;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    private int responseNumber;
    private String subFranchiseID;
    private String outletID;
    private String outletName;
    private String brandID;
    private String address;
    private String neighbourhoodID;
    private String cityID;
    private String email;
    private String timings;
    private String cityRank;
    private String latitude;
    private String longitude;
    private String pincode;
    private String landmark;
    private String streetname;
    private String brandName;
    private String outletURL;
    private String numCoupons;
    private String neighbourhoodName;
    private String phoneNumber;
    private String cityName;
    private String distance;
    private Categories categories[];

    private String logoURL;
    private String coverURL;

    public Bitmap getLogoIcon() {
        return logoIcon;
    }

    public void setLogoIcon(Bitmap logoIcon) {
        this.logoIcon = logoIcon;
    }

    private Bitmap logoIcon;

}
