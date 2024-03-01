package com.kirikos.smartalert.backend;

import com.google.firebase.firestore.GeoPoint;

public class DangerCase {
    private String dangerType;
    private int numOfRep;
    private GeoPoint location;
    private long timestamp;

    public String getDangerType() { return dangerType; }
    public void setDangerType(String dangerType) { this.dangerType = dangerType; }

    public int getNumOfRep() { return numOfRep; }
    public void setNumOfRep(int numOfRep) { this.numOfRep = numOfRep; }

    public GeoPoint getLocation() { return location; }
    public void setLocation(GeoPoint location) { this.location = location; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}