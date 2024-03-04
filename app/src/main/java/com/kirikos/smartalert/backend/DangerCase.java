package com.kirikos.smartalert.backend;

import com.google.firebase.firestore.GeoPoint;

public class DangerCase {
    private String dangerType;
    private int numOfRep;
    private GeoPoint location;
    private long timestamp;
    private String dc_key;

    public DangerCase() {
    }

    public String getDangerType() { return dangerType; }
    public void setDangerType(String dangerType) { this.dangerType = dangerType; }

    public int getNumOfRep() { return numOfRep; }
    public void setNumOfRep(int numOfRep) { this.numOfRep = numOfRep; }

    public GeoPoint getLocation() { return location; }
    public void setLocation(GeoPoint location) { this.location = location; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public String getKey() { return dc_key; }
    public void setKey(String key) { this.dc_key = dc_key; }
}