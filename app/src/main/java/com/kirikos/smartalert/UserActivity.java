package com.kirikos.smartalert;

import static android.location.LocationManager.GPS_PROVIDER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.GeoPoint;

import java.util.Date;
import java.util.Locale;

public class UserActivity extends AppCompatActivity{
    FirebaseDatabase db;
    DatabaseReference ref;
    Spinner spinner;
    EditText text;
    String type;
    String comment;
    LocationManager locationManager;
    GeoPoint location;
    long timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //get the spinner from the xml.
        spinner = findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.danger_types_gr,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        //edittext
        text = findViewById(R.id.edit_text);

        //database
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("cases/pending");
    }
    public void addImage(View view) {
        // add image code
    }
    public void submit(View view) {
        // submit case code
        type = spinner.getSelectedItem().toString();
        comment = text.getText().toString();
        location = gps();
        timestamp = System.currentTimeMillis();
        Log.i("timestamp", String.valueOf(timestamp));

        Case c = new Case(type,comment,location,timestamp);
        Log.i("case object", String.valueOf(c));

        ref.push().setValue(c);
    }
    public GeoPoint gps() {
        Location loc;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        }
        loc = locationManager.getLastKnownLocation(GPS_PROVIDER);
        double latitude = loc.getLatitude();
        double longitude = loc.getLongitude();
        return new GeoPoint(latitude,longitude);
    }
}