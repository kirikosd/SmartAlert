package com.kirikos.smartalert.database;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.backend.DangerCase;
import com.kirikos.smartalert.backend.Report;
import com.kirikos.smartalert.employee.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRefReports = db.getReference("reports");
    private DatabaseReference dbRefCasesAccepted = db.getReference("cases/accepted");
    private DatabaseReference dbRefCasesPending = db.getReference("cases/pending");
    private DatabaseReference dbRefCasesIgnored = db.getReference("cases/ignored");

    public void pushReport(Report r){
        if(r.getType().equals("Πυρκαγιά") || r.getType().equals("Fire")){
            r.setType("fire");
        } else if(r.getType().equals("Σεισμός") || r.getType().equals("Earthquake")){
            r.setType("earthquake");
        } else if(r.getType().equals("Πλημμύρα") || r.getType().equals("Flood")){
            r.setType("flood");
        }

        dbRefReports.push().setValue(r);
    }
    public void retrieveReports(ReportCallback reportCallback){
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
                        reportCallback.onCallback(itemList);
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
    }
    public void pushAcceptedCase(DangerCase dc){
        dbRefCasesAccepted.push().setValue(dc);
    }
    public void pushPendingCase(DangerCase dc){
        dbRefCasesPending.push().setValue(dc);
    }

    public void retrievePendingCases(DangerCaseCallback dangerCaseCallback){
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
                        dangerCaseCallback.onCallback(itemList);
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
    }
    public List<DangerCase> retrieveAcceptedCases(){
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
                    }
                } else {
                    Log.d("datasnapshot","does not exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting PendingCase failed, log a message
                Log.w(TAG, "loadAcceptedCase:onCancelled", databaseError.toException());
            }
        };
        dbRefCasesAccepted.addValueEventListener(caseListener);
        return itemList;
    }
    public void pushIgnoredCase(DangerCase dc){
        dbRefCasesIgnored.push().setValue(dc);
    }

}