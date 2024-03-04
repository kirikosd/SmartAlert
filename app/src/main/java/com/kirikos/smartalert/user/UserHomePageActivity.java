package com.kirikos.smartalert.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.auth.SignInActivity;

public class UserHomePageActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);

        if (ActivityCompat.checkSelfPermission(UserHomePageActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},123);
        }
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
        finish();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
    }
}