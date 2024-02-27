package com.kirikos.smartalert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class EmpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MyItem i1 = new MyItem();
        i1.setDangerType("i1 danger");
        i1.setNumOfRep("i1 num");
        i1.setLocation("i1 loc");
        MyItem i2 = new MyItem();
        i2.setDangerType("i2 danger");
        i2.setNumOfRep("i2 num");
        i2.setLocation("i2 loc");

        List<MyItem> itemList = new ArrayList<>(); // Populate with your data
        itemList.add(i1);
        itemList.add(i2);
        itemList.add(i2);
        itemList.add(i2);
        itemList.add(i2);
        itemList.add(i2);
        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}