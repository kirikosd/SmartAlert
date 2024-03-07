package com.kirikos.smartalert.ui;

import android.icu.text.SimpleDateFormat;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.kirikos.smartalert.backend.DangerCase;
import com.kirikos.smartalert.R;
import java.util.Date;
import java.util.Locale;

public class DangerCaseCardViewHolder extends RecyclerView.ViewHolder {
    private TextView dangerTypeTextView;
    private TextView numOfRepTextView;
    private TextView locationTextView;
    private TextView timestampTextView;

    private String dangerType;
    private String numOfRep;
    private String location;
    private long timestamp;
    private String formattedDate;

    public DangerCaseCardViewHolder(View itemView) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        formattedDate = sdf.format(date);

        dangerTypeTextView.setText(dangerType);
        numOfRepTextView.setText(numOfRep);
        locationTextView.setText(location);
        timestampTextView.setText(formattedDate);
    }
}