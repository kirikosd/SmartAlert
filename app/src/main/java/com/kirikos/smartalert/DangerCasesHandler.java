package com.kirikos.smartalert;

import static android.content.ContentValues.TAG;

import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    public List<DangerCase> findPotentialDangerCases(){
        // code to read pending cases from database
        // and calculate their severity

        // if they are pushed to the database
        // the path should be 'cases/pending'

        // read from db
        List<DangerCase> itemList = new ArrayList<>();
        ValueEventListener reportListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Report object and use the values to update the UI
                Report report = dataSnapshot.getValue(Report.class);
                DangerCase dc = new DangerCase();
                dc.setDangerType(report.getType());
                dc.setNumOfRep(2);
                dc.setLocation(report.getLocation());
                dc.setTimestamp(report.getTimestamp());

                itemList.add(dc);
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
    public void pushAcceptedDangerCases(){
        // code to update database with the
        // cases that have been accepted as dangerous
        // by the employee

        // When they are pushed to the database
        // the path should be 'cases/accepted'
    }
}