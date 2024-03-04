package com.kirikos.smartalert.user;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.employee.MyEmpAdapter;

import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;



public class DisplayStatisticsActivity extends AppCompatActivity {
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_statistics);

//        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewUser);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

//        MyEmpAdapter adapter = new MyEmpAdapter(dangerCaseList);
//        recyclerView.setAdapter(adapter);
    }
    public void save(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.apply();
    }
    public void onBackPressed(View view) {
        finish(); // Finish the current activity
        // Start the desired previous activity (if needed)
        Intent firstIntent = new Intent(getApplicationContext(), UserHomePageActivity.class);
        startActivity(firstIntent);
    }
}