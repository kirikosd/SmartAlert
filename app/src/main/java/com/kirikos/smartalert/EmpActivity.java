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

        DangerCase i = new DangerCase();
        i.setDangerType("i danger");
        i.setNumOfRep("i num");
        i.setLocation("i loc");

        //Display timestamp in proper date format
        String timestamp = "1708955723916";
//        Date date = new Date(timestamp);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        String formattedDate = sdf.format(date);

        i.setTimestamp(timestamp);

        List<DangerCase> itemList = new ArrayList<>(); // Populate with your data
        itemList.add(i);
        itemList.add(i);
        itemList.add(i);
        itemList.add(i);
        itemList.add(i);
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