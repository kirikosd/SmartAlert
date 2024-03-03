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

        // test object
        List<Report> itemList = new ArrayList<>();
        Report r = new Report();
        r.setType("backend");
        r.setLocation(new GeoPoint(2.5,3.6));
        r.setComment("fff");
        r.setTimestamp(System.currentTimeMillis());
        //

        List<Report> reports;
        dbHandler.retrieveReports(new ReportCallback() {
            @Override
            public void onCallback(List<Report> reportList) {
                Log.d("report list", String.valueOf(reportList));
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