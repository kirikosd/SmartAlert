package com.kirikos.smartalert.backend;

import android.location.Location;

import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.database.DangerCaseCallback;
import com.kirikos.smartalert.database.DatabaseHandler;
import com.kirikos.smartalert.database.ReportCallback;
import com.kirikos.smartalert.user.DisplayStatisticsActivity;
import com.kirikos.smartalert.user.StatisticsHandler;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DangerCasesHandler {
    DatabaseHandler dbHandler = new DatabaseHandler();
    StatisticsHandler sthandler = new StatisticsHandler();
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
        float distance;
        long timeDiff;
        DangerCase dc1 = new DangerCase();
        for (Report r1: reportList) {
            for (Report r2: reportList) {
                distance = getDistanceInMetres(r1.getLocation(), r2.getLocation());
                timeDiff = getTimeDifferenceInSeconds(r1.getTimestamp(), r2.getTimestamp());
                if (distance <= 10000 && timeDiff <= 7200) {    // 10km and 2h difference
                    dc1.setNumOfRep(dc1.getNumOfRep() + 1);
                } else {
                    DangerCase dc2 = new DangerCase();
                    dc2.setDangerType("fire");
                    dc2.setLocation(r2.getLocation());
                    dc2.setTimestamp(r2.getTimestamp());
                    dc2.setNumOfRep(1);
                }
            }
        }
        return  dangerCaseList;
    }
    public List<DangerCase> scanEarthquakes(List<Report> reportList){

        List<DangerCase> dangerCaseList = new ArrayList<>();
        return  dangerCaseList;
    }
    public List<DangerCase> scanFloods(List<Report> reportList){

        List<DangerCase> dangerCaseList = new ArrayList<>();
        return  dangerCaseList;
    }
    public void notifyUser(){
        // retrieves cases that are accepted as dangerous
        // and decides whether to notify user or not
        // based on danger type and distance

        // test object fir statistics tab
        DangerCase d = new DangerCase();
        d.setDangerType("danger test");
        d.setLocation(new GeoPoint(2.5,3.6));
        d.setNumOfRep(8);
        d.setTimestamp(System.currentTimeMillis());

        //sthandler.saveStat(d);

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
                        //notification
                    } else if (distance <= 100000 && dc.getDangerType().equals("earthquake")){
                        //notification
                    } else if (distance <= 15000 && dc.getDangerType().equals("flood")){
                        //notification
                    }
                }
            }
        });
    }
    public float getDistanceInMetres(GeoPoint p1, GeoPoint p2) {
        double lat1 = ((double)p1.getLatitude()) / 1e6;
        double lng1 = ((double)p1.getLongitude()) / 1e6;
        double lat2 = ((double)p2.getLatitude()) / 1e6;
        double lng2 = ((double)p2.getLongitude()) / 1e6;
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