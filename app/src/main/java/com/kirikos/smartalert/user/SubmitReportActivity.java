package com.kirikos.smartalert.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
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
import com.kirikos.smartalert.backend.Report;

public class SubmitReportActivity extends AppCompatActivity implements LocationListener {
    DatabaseHandler dbHandler = new DatabaseHandler();
    Spinner spinner;
    EditText text;
    String type;
    String comment;
    GeoPoint geoPoint;
    LocationManager locationManager;
    long timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},123);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        //get the spinner from the xml.
        spinner = findViewById(R.id.spinner1);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.danger_types,
                android.R.layout.simple_spinner_item
        );
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        //edittext
        text = findViewById(R.id.edit_text);
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        geoPoint = new GeoPoint(latitude, longitude);
    }
    public void addImage(View view) {
        // add image code
    }
    public void submit(View view) {
        // submit case code
        type = spinner.getSelectedItem().toString();
        comment = text.getText().toString();
        timestamp = System.currentTimeMillis();

        Report r = new Report(type,comment,geoPoint,timestamp);

        dbHandler.pushReport(r);
        Toast.makeText(getApplicationContext(), "Το περιστατικό υποβλήθηκε με επιτυχία!", Toast.LENGTH_LONG).show();
    }
    public void onBackPressed(View view) {
        finish(); // Finish the current activity
        // Start the desired previous activity (if needed)
        Intent firstIntent = new Intent(getApplicationContext(), UserHomePageActivity.class);
        startActivity(firstIntent);
    }
}