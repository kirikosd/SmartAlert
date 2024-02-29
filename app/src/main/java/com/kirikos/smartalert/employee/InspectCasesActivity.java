package com.kirikos.smartalert.employee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.kirikos.smartalert.logic.DangerCase;
import com.kirikos.smartalert.logic.DangerCasesHandler;
import com.kirikos.smartalert.R;

import java.util.ArrayList;
import java.util.List;

public class InspectCasesActivity extends AppCompatActivity {
    DangerCasesHandler handler = new DangerCasesHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspect_cases);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<DangerCase> itemList; // Populate with your data
        itemList = handler.findPotentialDangerCases();
        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
    public void ignoreCase() {
        // code for ignoring case
    }
    public void acceptCase() {
        // code for accepting case as dangerous
    }
}