package com.kirikos.smartalert.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kirikos.smartalert.R;

public class UserHomePageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
    }
    public void goToSubmitReport(View view) {
        Intent intent = new Intent(this, SubmitReportActivity.class);
        Log.d("goToSubmitReport",String.valueOf(intent));

        startActivity(intent);
        Log.d("goToSubmitReport","intent started");
    }
    public void goToDisplayStatistics(View view) {
        Intent intent = new Intent(this, DisplayStatisticsActivity.class);
        Log.d("goToDisplayStatistics",String.valueOf(intent));
        startActivity(intent);
        Log.d("goToDisplayStatistics","intent started");
    }
}