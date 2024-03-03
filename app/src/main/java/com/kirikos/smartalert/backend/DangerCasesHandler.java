package com.kirikos.smartalert.backend;

import android.util.Log;

import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.database.DatabaseHandler;
import com.kirikos.smartalert.database.ReportCallback;

import java.util.ArrayList;
import java.util.List;

public class DangerCasesHandler {
    DatabaseHandler dbHandler = new DatabaseHandler();
    public void findPotentialDangerCases(){
        // in this method we will be processing the reports we retrieve
        // with above method and add logic to find DangerCases
        // and return them in a list for the employee to inspect

        dbHandler.retrieveFireReports(new ReportCallback() {
            @Override
            public void onCallback(List<Report> reportList) {
                // processing code goes here
                DangerCase dc = new DangerCase();
                String type = "fire";
                int nor = 0;
                GeoPoint centerLocation = new GeoPoint(1.5,2.5);
                long timestamp = 1;

                for (Report r: reportList) {
                    // code to handle fire cases
                    timestamp = r.getTimestamp();
                    nor += 1;
                    centerLocation = new GeoPoint(r.getLocation().getLatitude(),r.getLocation().getLongitude());
                }
                dc.setDangerType(type);
                dc.setLocation(centerLocation);
                dc.setNumOfRep(nor);
                dc.setTimestamp(timestamp);
                dbHandler.pushPendingCase(dc);
            }
        });

        dbHandler.retrieveEarthquakeReports(new ReportCallback() {
            @Override
            public void onCallback(List<Report> reportList) {
                // processing code goes here
                List<DangerCase> dangerCaseList = new ArrayList<>();
                for (Report r: reportList) {
                    // code to handle earthquake cases
                }
            }
        });

        dbHandler.retrieveFloodReports(new ReportCallback() {
            @Override
            public void onCallback(List<Report> reportList) {
                // processing code goes here
                List<DangerCase> dangerCaseList = new ArrayList<>();
                for (Report r: reportList) {
                    // code to handle flood cases
                }
            }
        });
    }
    public void notifyUser(){
        // retrieves cases that are accepted as dangerous
        // and decides whether to notify user or not
        // based on danger type and distance
        List<DangerCase> dangerCases = dbHandler.retrieveAcceptedCases();
    }
}