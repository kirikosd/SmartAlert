package com.kirikos.smartalert.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.backend.DangerCase;
import com.kirikos.smartalert.backend.Report;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRefReports = db.getReference("reports");
    private DatabaseReference dbRefCasesAccepted = db.getReference("cases/accepted");
    private DatabaseReference dbRefCasesPending = db.getReference("cases/pending");
    private DatabaseReference dbRefCasesIgnored = db.getReference("cases/ignored");

    public void pushReport(Report r){
        dbRefReports.push().setValue(r);
    }
    public List<Report> retrieveReports(){
        // reads all reports from database and returns them in a list
        List<Report> itemList = new ArrayList<>();
        ValueEventListener reportListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get DangerCase object and use the values to update the UI
                    for (DataSnapshot child : dataSnapshot.getChildren()) {

                        Report rep = new Report();
                        rep.setType(String.valueOf(child.child("type").getValue()));
                        rep.setComment(String.valueOf(child.child("comment").getValue()));
                        rep.setTimestamp(((Long) child.child("timestamp").getValue()).intValue());
                        rep.setLocation(new GeoPoint(
                                (Double) child.child("location/latitude").getValue(),
                                (Double) child.child("location/longitude").getValue()));

                        itemList.add(rep);
                        Log.d("itemlist", String.valueOf(itemList));
                    }
                } else {
                    Log.d("datasnapshot","does not exist");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Report failed, log a message
                Log.w(TAG, "loadReport:onCancelled", databaseError.toException());
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
        ValueEventListener caseListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get DangerCase object and use the values to update the UI
                    for (DataSnapshot child : dataSnapshot.getChildren()) {

                        DangerCase dc = new DangerCase();
                        dc.setDangerType(String.valueOf(child.child("dangerType").getValue()));
                        dc.setNumOfRep(((Long) child.child("numOfRep").getValue()).intValue());
                        dc.setTimestamp(((Long) child.child("timestamp").getValue()).intValue());
                        dc.setLocation(new GeoPoint(
                                (Double) child.child("location/latitude").getValue(),
                                (Double) child.child("location/longitude").getValue()));

                        itemList.add(dc);
                        Log.d("itemlist", String.valueOf(itemList));
                    }
                } else {
                    Log.d("datasnapshot","does not exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting PendingCase failed, log a message
                Log.w(TAG, "loadPendingCase:onCancelled", databaseError.toException());
            }
        };
        dbRefCasesPending.addValueEventListener(caseListener);
        return itemList;
    }
    public void pushIgnoredCase(DangerCase dc){
        dbRefCasesIgnored.push().setValue(dc);
    }

}