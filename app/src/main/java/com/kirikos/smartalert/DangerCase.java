package com.kirikos.smartalert;

public class DangerCase {
    private String dangerType;
    private String numOfRep;
    private String location;
    private String timestamp;

    public String getDangerType() { return dangerType; }
    public void setDangerType(String dangerType) { this.dangerType = dangerType; }

    public String getNumOfRep() { return numOfRep; }
    public void setNumOfRep(String numOfRep) { this.numOfRep = numOfRep; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}