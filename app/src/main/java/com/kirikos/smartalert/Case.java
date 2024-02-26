package com.kirikos.smartalert;

import com.google.firebase.firestore.GeoPoint;
import java.time.LocalDate;


public class Case {
    private String type;
    private String comment;

    //private String image;
    private GeoPoint location;
    private LocalDate t;

    public Case(String type, String comment, GeoPoint location, LocalDate t) {
        this.type = type;
        this.comment = comment;
        this.location = location;
        this.t = t;
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
    public LocalDate getT() { return t; }
    public void setT(LocalDate t) {
        this.t = t;
    }
}