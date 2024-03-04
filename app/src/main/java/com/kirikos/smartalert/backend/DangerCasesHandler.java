package com.kirikos.smartalert.backend;

import android.location.Location;

import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.database.DangerCaseCallback;
import com.kirikos.smartalert.database.DatabaseHandler;
import com.kirikos.smartalert.database.ReportCallback;
import com.kirikos.smartalert.user.StatisticsHandler;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DangerCasesHandler {
    DatabaseHandler dbHandler = new DatabaseHandler();
//    StatisticsHandler sthandler = new StatisticsHandler();
    public void findPotentialDangerCases(){
        // in this method we will be processing the reports we retrieve
        // with below methods and add logic to find DangerCases
        // and return them in a list for the employee to inspect

        dbHandler.retrieveFireReports(new ReportCallback() {
            @Override
            public void onCallback(List<Report> reportList) {
                List<DangerCase> dangerCaseList = scanFires(reportList);

                for (DangerCase dc: dangerCaseList) {
                    dbHandler.pushPendingCase(dc);
                }
            }
        });

        dbHandler.retrieveEarthquakeReports(new ReportCallback() {
            @Override
            public void onCallback(List<Report> reportList) {
                List<DangerCase> dangerCaseList = scanEarthquakes(reportList);

                for (DangerCase dc: dangerCaseList) {
                    dbHandler.pushPendingCase(dc);
                }
            }
        });

        dbHandler.retrieveFloodReports(new ReportCallback() {
            @Override
            public void onCallback(List<Report> reportList) {
                List<DangerCase> dangerCaseList = scanFloods(reportList);

                for (DangerCase dc: dangerCaseList) {
                    dbHandler.pushPendingCase(dc);
                }
            }
        });
    }
    public List<DangerCase> scanFires(List<Report> reportList){

        List<DangerCase> dangerCaseList = new ArrayList<>();
        float distance = getDistanceInMetres(reportList.get(0).getLocation(), reportList.get(1).getLocation());
        long timeDiff = getTimeDifferenceInSeconds(reportList.get(0).getTimestamp(), reportList.get(1).getTimestamp());
        boolean happenedToday = getTimeDifferenceInSeconds(System.currentTimeMillis(), reportList.get(0).getTimestamp()) <= 86400; // last 24h

        DangerCase dc = new DangerCase();
        dc.setDangerType("fire");
        dc.setLocation(reportList.get(0).getLocation());
        dc.setNumOfRep(0);
        dc.setTimestamp(reportList.get(0).getTimestamp());

        for (Report r: reportList) {
            if (happenedToday) {
                if (distance == 0 && timeDiff == 0) {
                    reportList.remove(r); // same report
                } else if (distance <= 10000 && timeDiff <= 7200) {  // 10km and 2h difference
                    dc.setNumOfRep(dc.getNumOfRep() + 1);            // puts them in the same danger case
                    scanFires(reportList);
                } else {
                    DangerCase dc2 = new DangerCase();
                    dc2.setDangerType("fire");              // different case
                    dc2.setLocation(r.getLocation());       // creates new danger case
                    dc2.setTimestamp(r.getTimestamp());
                    dc2.setNumOfRep(1);
                    reportList.remove(r);
                    scanFires(reportList);
                }
            } else {
                reportList.remove(r);
            }
        }
        return  dangerCaseList;
    }
    public List<DangerCase> scanEarthquakes(List<Report> reportList){

        List<DangerCase> dangerCaseList = new ArrayList<>();
        float distance = getDistanceInMetres(reportList.get(0).getLocation(), reportList.get(1).getLocation());
        long timeDiff = getTimeDifferenceInSeconds(reportList.get(0).getTimestamp(), reportList.get(1).getTimestamp());
        boolean happenedToday = getTimeDifferenceInSeconds(System.currentTimeMillis(), reportList.get(0).getTimestamp()) <= 86400; // last 24h

        DangerCase dc = new DangerCase();
        dc.setDangerType("earthquake");
        dc.setLocation(reportList.get(0).getLocation());
        dc.setNumOfRep(0);
        dc.setTimestamp(reportList.get(0).getTimestamp());

        for (Report r: reportList) {
            if (happenedToday) {
                if (distance == 0 && timeDiff == 0) {
                    reportList.remove(r); // same report
                } else if (distance <= 100000 && timeDiff <= 600) {  // 100km and 10m difference
                    dc.setNumOfRep(dc.getNumOfRep() + 1);            // puts them in the same danger case
                    scanFires(reportList);
                } else {
                    DangerCase dc2 = new DangerCase();
                    dc2.setDangerType("earthquake");              // different case
                    dc2.setLocation(r.getLocation());       // creates new danger case
                    dc2.setTimestamp(r.getTimestamp());
                    dc2.setNumOfRep(1);
                    reportList.remove(r);
                    scanFires(reportList);
                }
            } else {
                reportList.remove(r);
            }
        }
        return  dangerCaseList;
    }
    public List<DangerCase> scanFloods(List<Report> reportList){

        List<DangerCase> dangerCaseList = new ArrayList<>();
        float distance = getDistanceInMetres(reportList.get(0).getLocation(), reportList.get(1).getLocation());
        long timeDiff = getTimeDifferenceInSeconds(reportList.get(0).getTimestamp(), reportList.get(1).getTimestamp());
        boolean happenedToday = getTimeDifferenceInSeconds(System.currentTimeMillis(), reportList.get(0).getTimestamp()) <= 86400; // last 24h

        DangerCase dc = new DangerCase();
        dc.setDangerType("flood");
        dc.setLocation(reportList.get(0).getLocation());
        dc.setNumOfRep(0);
        dc.setTimestamp(reportList.get(0).getTimestamp());

        for (Report r: reportList) {
            if (happenedToday) {
                if (distance == 0 && timeDiff == 0) {
                    reportList.remove(r); // same report
                } else if (distance <= 15000 && timeDiff <= 43200) {  // 15km and 12h difference
                    dc.setNumOfRep(dc.getNumOfRep() + 1);            // puts them in the same danger case
                    scanFires(reportList);
                } else {
                    DangerCase dc2 = new DangerCase();
                    dc2.setDangerType("flood");              // different case
                    dc2.setDangerType("flood");              // different case
                    dc2.setLocation(r.getLocation());       // creates new danger case
                    dc2.setTimestamp(r.getTimestamp());
                    dc2.setNumOfRep(1);
                    reportList.remove(r);
                    scanFires(reportList);
                }
            } else {
                reportList.remove(r);
            }
        }
        return  dangerCaseList;
    }
    public void notifyUser(){
        // retrieves cases that are accepted as dangerous
        // and decides whether to notify user or not
        // based on danger type and distance

        // test object fir statistics tab
//        DangerCase d = new DangerCase();
//        d.setDangerType("danger test");
//        d.setLocation(new GeoPoint(2.5,3.6));
//        d.setNumOfRep(8);
//        d.setTimestamp(System.currentTimeMillis());
        // sthandler.saveStat(dc);

        GeoPoint userLocation = new GeoPoint(1.0,1.0);
//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
//
//        @Override
//        public void onLocationChanged(@NonNull Location location) {
//            double latitude = location.getLatitude();
//            double longitude = location.getLongitude();
//            userLocation = new GeoPoint(latitude, longitude);
//        }

        List<DangerCase> dangerCases = dbHandler.retrieveAcceptedCases(new DangerCaseCallback() {
            @Override
            public void onCallback(List<DangerCase> dangerCaseList) {
                for (DangerCase dc: dangerCaseList) {
                    // calculate distance for danger case to userLocation
                    float distance = getDistanceInMetres(userLocation, dc.getLocation());
                    // notify user
                    if (distance <= 10000 && dc.getDangerType().equals("fire")){
                        // notification
                        // sthandler.saveStat(dc);
                    } else if (distance <= 100000 && dc.getDangerType().equals("earthquake")){
                        // notification
                        // sthandler.saveStat(dc);
                    } else if (distance <= 15000 && dc.getDangerType().equals("flood")){
                        // notification
                        // sthandler.saveStat(dc);
                    }
                }
            }
        });
    }
    public float getDistanceInMetres(GeoPoint p1, GeoPoint p2) {
        double lat1 = p1.getLatitude() / 1e6;
        double lng1 = p1.getLongitude() / 1e6;
        double lat2 = p2.getLatitude() / 1e6;
        double lng2 = p2.getLongitude() / 1e6;
        float [] dist = new float[1];
        Location.distanceBetween(lat1, lng1, lat2, lng2, dist);
        return dist[0];
    }
    public Long getTimeDifferenceInSeconds(long t1, long t2) {
        Instant timestamp1 = Instant.ofEpochSecond(t1);
        Instant timestamp2 = Instant.ofEpochSecond(t2);
        Duration d = Duration.between(timestamp1, timestamp2);
        return d.getSeconds();
    }
}