package com.kirikos.smartalert.user;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.kirikos.smartalert.R;

public class UserHomePageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);
    }
    public void goToSubmitReport(View view) {
        Intent intent = new Intent(this, SubmitReportActivity.class);
        startActivity(intent);
    }
    public void goToDisplayStatistics(View view) {
        Intent intent = new Intent(this, DisplayStatisticsActivity.class);
        startActivity(intent);
    }
    public void signOut(View view) {
        FirebaseAuth.getInstance().signOut();
    }
}