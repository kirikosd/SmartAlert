package com.kirikos.smartalert.login;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.employee.InspectCasesActivity;
import com.kirikos.smartalert.user.UserHomePageActivity;

public class LoginPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }
    public void goToUser(View view) {
        Intent intent = new Intent(this, UserHomePageActivity.class);
        startActivity(intent);
    }
    public void goToEmp(View view) {
        Intent intent = new Intent(this, InspectCasesActivity.class);
        startActivity(intent);
    }
}