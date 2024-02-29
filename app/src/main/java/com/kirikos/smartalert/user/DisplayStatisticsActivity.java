package com.kirikos.smartalert.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kirikos.smartalert.R;

public class DisplayStatisticsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_statistics);
    }
    public void onBackPressed(View view) {
        finish(); // Finish the current activity
        // Start the desired previous activity (if needed)
        Intent firstIntent = new Intent(getApplicationContext(), UserHomePageActivity.class);
        startActivity(firstIntent);
    }
}