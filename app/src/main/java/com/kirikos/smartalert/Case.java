package com.kirikos.smartalert;

import android.location.Location;

public class Case {
    private String type;
    private String comment;

    //private String image;
    //private Location location;

    public Case(String type, String comment) {
        this.type = type;
        this.comment = comment;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
}