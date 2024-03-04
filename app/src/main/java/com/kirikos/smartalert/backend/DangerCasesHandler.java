package com.kirikos.smartalert.backend;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.MainActivity;
import com.kirikos.smartalert.database.DangerCaseCallback;
import com.kirikos.smartalert.database.DatabaseHandler;
import com.kirikos.smartalert.database.ReportCallback;
import com.kirikos.smartalert.employee.MyAdapter;
import com.kirikos.smartalert.user.UserHomePageActivity;

import java.util.ArrayList;
import java.util.List;

public class DangerCasesHandler {
    DatabaseHandler dbHandler = new DatabaseHandler();
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
        DangerCase dc1 = new DangerCase();
        for (Report r1: reportList) {
            for (Report r2: reportList) {
                distance = getDistanceInMetres(r1.getLocation(), r2.getLocation());
                if (distance <= 10000) {
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
}