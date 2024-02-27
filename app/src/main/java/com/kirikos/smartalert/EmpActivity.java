package com.kirikos.smartalert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EmpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MyItem i = new MyItem();
        i.setDangerType("i1 danger");
        i.setNumOfRep("i1 num");
        i.setLocation("i1 loc");

        //Display timestamp in proper date format
        String timestamp = "1708955723916";
//        Date date = new Date(timestamp);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        String formattedDate = sdf.format(date);

        i.setTimestamp(timestamp);

        List<MyItem> itemList = new ArrayList<>(); // Populate with your data
        itemList.add(i);
        itemList.add(i);
        itemList.add(i);
        itemList.add(i);
        itemList.add(i);
        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}