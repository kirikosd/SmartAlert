package com.kirikos.smartalert.employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.kirikos.smartalert.backend.DangerCase;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.ui.DangerCaseCardViewHolder;

import java.util.List;

public class MyEmpAdapter extends RecyclerView.Adapter<DangerCaseCardViewHolder> {
    private List<DangerCase> itemList;

    public MyEmpAdapter(List<DangerCase> itemList) {
        this.itemList = itemList;
    }

    @Override
    public DangerCaseCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout_emp, parent, false);
        return new DangerCaseCardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DangerCaseCardViewHolder holder, int position) {
        DangerCase item = itemList.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}