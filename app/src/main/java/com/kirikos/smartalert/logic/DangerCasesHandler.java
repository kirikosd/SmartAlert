package com.kirikos.smartalert.logic;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class DangerCasesHandler {;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("reports");
    public List<Report> retrieveReports(){
        // reads all reports fro database and returns them in a list
        List<Report> itemList = new ArrayList<>();
        ValueEventListener reportListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Report object and use the values to update the UI
                Report report = dataSnapshot.getValue(Report.class);
                itemList.add(report);
                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbRef.addValueEventListener(reportListener);
        return itemList;
    }
    public List<DangerCase> findPotentialDangerCases(){
        // in this method we will be processing the reports we retrieve
        // with above method and add logic to find DangerCases
        // and return them in a list for the employee to inspect

        List<Report> reports = retrieveReports();

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