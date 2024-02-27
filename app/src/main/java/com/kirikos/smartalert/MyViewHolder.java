package com.kirikos.smartalert;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView dangerTypeTextView;
    private TextView numOfRepTextView;
    private TextView locationTextView;

    public MyViewHolder(View itemView) {
        super(itemView);
        dangerTypeTextView = itemView.findViewById(R.id.dangerTypeTextView);
        numOfRepTextView = itemView.findViewById(R.id.numOfRepTextView);
        locationTextView = itemView.findViewById(R.id.locationTextView);
    }

    public void bindData(MyItem item) {
        dangerTypeTextView.setText(item.getDangerType());
        numOfRepTextView.setText(item.getNumOfRep());
        locationTextView.setText(item.getLocation());
    }
}