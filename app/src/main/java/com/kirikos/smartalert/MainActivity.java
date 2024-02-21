package com.kirikos.smartalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goToUser(View view) {
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
    public void goToEmp(View view) {
        Intent intent = new Intent(this, EmpActivity.class);
        startActivity(intent);
    }
}