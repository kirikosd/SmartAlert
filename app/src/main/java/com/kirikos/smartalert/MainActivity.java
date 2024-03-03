package com.kirikos.smartalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.kirikos.smartalert.auth.SignInActivity;
import com.kirikos.smartalert.backend.DangerCasesHandler;

public class MainActivity extends AppCompatActivity {
    DangerCasesHandler dch = new DangerCasesHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dch.findPotentialDangerCases();
        //dch.notifyUser();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}