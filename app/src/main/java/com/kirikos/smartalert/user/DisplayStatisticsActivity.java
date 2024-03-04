package com.kirikos.smartalert.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kirikos.smartalert.R;
import com.kirikos.smartalert.backend.DangerCase;

import java.util.List;

public class DisplayStatisticsActivity extends AppCompatActivity {
    List<DangerCase> dangerCaseList;
    StatisticsHandler stHandler = new StatisticsHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_statistics);

        dangerCaseList = stHandler.retrieveStats();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewUser);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MyUserAdapter adapter = new MyUserAdapter(dangerCaseList);
        recyclerView.setAdapter(adapter);
    }
    public void onBackPressed(View view) {
        finish(); // Finish the current activity
        // Start the desired previous activity (if needed)
        Intent firstIntent = new Intent(getApplicationContext(), UserHomePageActivity.class);
        startActivity(firstIntent);
    }
}