package com.kirikos.smartalert.database;

import static android.content.ContentValues.TAG;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kirikos.smartalert.backend.DangerCase;
import com.kirikos.smartalert.backend.Report;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRefReports = db.getReference("reports");
    private DatabaseReference dbRefCasesAccepted = db.getReference("cases/accepted");
    private DatabaseReference dbRefCasesPending = db.getReference("cases/pending");

    public void pushReport(Report r){
        dbRefReports.push().setValue(r);
    }
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
        dbRefReports.addValueEventListener(reportListener);
        return itemList;
    }
    public void pushAcceptedCase(DangerCase dc){
        dbRefCasesAccepted.push().setValue(dc);
    }
    public void pushPendingCase(DangerCase dc){
        dbRefCasesPending.push().setValue(dc);
    }

    public List<DangerCase> retrievePendingCases(){
        List<DangerCase> itemList = new ArrayList<>();
        ValueEventListener reportListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get DangerCase object and use the values to update the UI
                DangerCase dc = dataSnapshot.getValue(DangerCase.class);
                itemList.add(dc);
                // ..
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbRefCasesPending.addValueEventListener(reportListener);
        return itemList;
    }
}