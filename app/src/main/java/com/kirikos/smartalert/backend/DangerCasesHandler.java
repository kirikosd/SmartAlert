package com.kirikos.smartalert.backend;

import android.util.Log;

import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.database.DatabaseHandler;
import java.util.ArrayList;
import java.util.List;

public class DangerCasesHandler {
    DatabaseHandler dbHandler = new DatabaseHandler();
    public void findPotentialDangerCases(){
        // in this method we will be processing the reports we retrieve
        // with above method and add logic to find DangerCases
        // and return them in a list for the employee to inspect

        List<Report> reports = dbHandler.retrieveReports();
        Log.d("reports",String.valueOf(reports));

        // test object
        List<DangerCase> itemList = new ArrayList<>();
        DangerCase dc = new DangerCase();
        dc.setDangerType("mxmxm");
        dc.setLocation(new GeoPoint(2.5,3.6));
        dc.setNumOfRep(1);
        dc.setTimestamp(System.currentTimeMillis());
        //

        dbHandler.pushPendingCase(dc);
    }
    public void notifyUser(){
        // retrieves cases that are accepted as dangerous
        // and decides whether to notify user or not
        // based on danger type and distance
        List<DangerCase> dangerCases = dbHandler.retrieveAcceptedCases();
    }
}