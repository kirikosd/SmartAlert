package com.kirikos.smartalert.user;

import static android.location.LocationManager.GPS_PROVIDER;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.firestore.GeoPoint;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.database.DatabaseHandler;
import com.kirikos.smartalert.logic.Report;

public class SubmitReportActivity extends AppCompatActivity {
    DatabaseHandler dbHandler = new DatabaseHandler();
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
        setContentView(R.layout.activity_submit_report);

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

        Report r = new Report(type,comment,location,timestamp);

        dbHandler.pushReport(r);
        Toast.makeText(getApplicationContext(), "Το περιστατικό υποβλήθηκε με επιτυχία!", Toast.LENGTH_LONG).show();
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

    public void onBackPressed(View view) {
        finish(); // Finish the current activity
        // Start the desired previous activity (if needed)
        Intent firstIntent = new Intent(getApplicationContext(), UserHomePageActivity.class);
        startActivity(firstIntent);
    }
}