package com.kirikos.smartalert;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class EmpActivity extends AppCompatActivity {
    DangerCasesHandler handler = new DangerCasesHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<DangerCase> itemList = new ArrayList<>(); // Populate with your data
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