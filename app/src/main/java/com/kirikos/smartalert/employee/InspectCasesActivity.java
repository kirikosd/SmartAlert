package com.kirikos.smartalert.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.database.DatabaseHandler;
import com.kirikos.smartalert.backend.DangerCase;
import com.kirikos.smartalert.backend.DangerCasesHandler;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.login.LoginPageActivity;
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

        List<DangerCase> itemList;
        itemList = dbHandler.retrievePendingCases();
        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
    public void ignoreCase(View view) {
        // code for ignoring case

        // test object
        DangerCase dc = new DangerCase();
        dc.setDangerType("danger test");
        dc.setLocation(new GeoPoint(2.5,3.6));
        dc.setNumOfRep(8);
        dc.setTimestamp(System.currentTimeMillis());
        //
        dbHandler.pushIgnoredCase(dc);
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
    public void onBackPressed(View view) {
        finish(); // Finish the current activity
        // Start the desired previous activity (if needed)
        Intent firstIntent = new Intent(getApplicationContext(), LoginPageActivity.class);
        startActivity(firstIntent);
    }
}