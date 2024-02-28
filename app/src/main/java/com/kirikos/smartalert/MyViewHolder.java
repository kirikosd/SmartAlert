package com.kirikos.smartalert;

import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.Date;
import java.util.Locale;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView dangerTypeTextView;
    private TextView numOfRepTextView;
    private TextView locationTextView;
    private TextView timestampTextView;

    private String dangerType;
    private String numOfRep;
    private String location;
    private long timestamp;
    private String formattedDate;

    public MyViewHolder(View itemView) {
        super(itemView);
        dangerTypeTextView = itemView.findViewById(R.id.dangerTypeTextView);
        numOfRepTextView = itemView.findViewById(R.id.numOfRepTextView);
        locationTextView = itemView.findViewById(R.id.locationTextView);
        timestampTextView = itemView.findViewById(R.id.timestampTextView);
    }

    public void bindData(DangerCase item) {
        dangerType = item.getDangerType();
        numOfRep = String.valueOf(item.getNumOfRep());
        location = item.getLocation().toString();
        timestamp = item.getTimestamp();

        //Display timestamp in proper date format
        Date date = new Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        formattedDate = sdf.format(date);

        dangerTypeTextView.setText(dangerType);
        numOfRepTextView.setText(numOfRep);
        locationTextView.setText(location);
        timestampTextView.setText(formattedDate);
    }
}