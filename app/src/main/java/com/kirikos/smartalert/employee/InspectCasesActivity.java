package com.kirikos.smartalert.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.database.DangerCaseCallback;
import com.kirikos.smartalert.database.DatabaseHandler;
import com.kirikos.smartalert.backend.DangerCase;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.auth.SignInActivity;

import java.util.ArrayList;
import java.util.List;

public class InspectCasesActivity extends AppCompatActivity {
    DatabaseHandler dbHandler = new DatabaseHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspect_cases);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        dbHandler.retrievePendingCases(new DangerCaseCallback() {
            @Override
            public void onCallback(List<DangerCase> dangerCaseList) {
                MyAdapter adapter = new MyAdapter(dangerCaseList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
    public void ignoreCase(View view) {
        // code for ignoring case
        Log.d("f", "IGNORE CASE " + this.toString());
        String d = (String) view.getTag();
        Log.d("f", "IGNORE CASE STRING " + d);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.
        // test object
        DangerCase dc = new DangerCase();
        dc.setDangerType("danger test");
        dc.setLocation(new GeoPoint(2.5,3.6));
        dc.setNumOfRep(8);
        dc.setTimestamp(System.currentTimeMillis());
        //
        dbHandler.pushIgnoredCase(dc);
        Toast.makeText(getApplicationContext(), "Το περιστατικό αγνοήθηκε με επιτυχία!", Toast.LENGTH_LONG).show();
    }
    public void acceptCase(View view) {
        // code for accepting case as dangerous

        // test object
        DangerCase dc = new DangerCase();
        dc.setDangerType("danger test");
        dc.setLocation(new GeoPoint(2.5,3.6));
        dc.setNumOfRep(8);
        dc.setTimestamp(System.currentTimeMillis());
        //
        dbHandler.pushAcceptedCase(dc);
        Toast.makeText(getApplicationContext(), "Το περιστατικό υποβλήθηκε με επιτυχία!", Toast.LENGTH_LONG).show();
    }
    public void signOut(View view) {
        finish();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
    }
    public void refresh(View view){
        finish();
        Intent intent = new Intent(getApplicationContext(), InspectCasesActivity.class);
        startActivity(intent);
    }
    public void onBackPressed(View view) {
        finish(); // Finish the current activity
        // Start the desired previous activity (if needed)
        Intent firstIntent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(firstIntent);
    }
}