package com.kirikos.smartalert.backend;

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

        // test object
        List<DangerCase> itemList = new ArrayList<>();
        DangerCase dc = new DangerCase();
        dc.setDangerType("danger test");
        dc.setLocation(new GeoPoint(2.5,3.6));
        dc.setNumOfRep(8);
        dc.setTimestamp(System.currentTimeMillis());
        //

        dbHandler.pushPendingCase(dc);
    }
}