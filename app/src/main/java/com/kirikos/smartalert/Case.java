package com.kirikos.smartalert;

import com.google.firebase.firestore.GeoPoint;


public class Case {
    private String type;
    private String comment;

    //private String image;
    private GeoPoint location;

    public Case(String type, String comment, GeoPoint location) {
        this.type = type;
        this.comment = comment;
        this.location = location;
    }
    public String getType() { return type; }
    public void setType(String type) {
        this.type = type;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public GeoPoint getLocation() {
        return location;
    }
    public void setLocation(GeoPoint location) {
        this.location = location;
    }
}