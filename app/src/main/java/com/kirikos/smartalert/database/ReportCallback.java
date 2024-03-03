package com.kirikos.smartalert.database;

import com.kirikos.smartalert.backend.Report;

import java.util.List;

public interface ReportCallback {
    void onCallback(List<Report> reportList);
}
