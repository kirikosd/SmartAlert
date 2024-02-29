package com.kirikos.smartalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kirikos.smartalert.employee.InspectCasesActivity;
import com.kirikos.smartalert.login.LoginPageActivity;
import com.kirikos.smartalert.user.UserHomePageActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LoginPageActivity.class);
        startActivity(intent);
    }
}