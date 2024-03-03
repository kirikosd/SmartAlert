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
                List<DangerCase> dangerCaseList = new ArrayList<>();
                for (Report r: reportList) {
                    // code to handle fire cases
                }
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

//        dbHandler.pushPendingCase(r);
    }
    public void notifyUser(){
        // retrieves cases that are accepted as dangerous
        // and decides whether to notify user or not
        // based on danger type and distance
        List<DangerCase> dangerCases = dbHandler.retrieveAcceptedCases();
    }
}