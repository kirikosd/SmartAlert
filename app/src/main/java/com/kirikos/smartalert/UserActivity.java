package com.kirikos.smartalert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
        //create a list of items for the spinner.
        String[] items = new String[]{"Πλημμύρα", "Πυρκαγιά", "Σεισμός"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
    public void addImage() {
        // add image code
    }
    public void submit() {
        // submit case code
    }
}