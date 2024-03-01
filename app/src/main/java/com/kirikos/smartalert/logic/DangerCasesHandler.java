package com.kirikos.smartalert.logic;

import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.database.DatabaseHandler;
import java.util.ArrayList;
import java.util.List;

public class DangerCasesHandler {
    DatabaseHandler dbHandler = new DatabaseHandler();
    public List<DangerCase> findPotentialDangerCases(){
        // in this method we will be processing the reports we retrieve
        // with above method and add logic to find DangerCases
        // and return them in a list for the employee to inspect

        List<Report> reports = dbHandler.retrieveReports();

        // test list
        List<DangerCase> itemList = new ArrayList<>();
        DangerCase dc = new DangerCase();
        dc.setDangerType("danger test");
        dc.setLocation(new GeoPoint(2.5,3.6));
        dc.setNumOfRep(8);
        dc.setTimestamp(System.currentTimeMillis());

        itemList.add(dc);
        itemList.add(dc);
        itemList.add(dc);
        itemList.add(dc);
        itemList.add(dc);
        //

        return itemList;
    }
    public void pushAcceptedDangerCases(){
        // code to update database with the
        // cases that have been accepted as dangerous
        // by the employee

        // When they are pushed to the database
        // the path should be 'cases/accepted'
    }
}