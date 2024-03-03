package com.kirikos.smartalert.database;

import com.kirikos.smartalert.backend.DangerCase;
import com.kirikos.smartalert.backend.Report;

import java.util.List;

public interface DangerCaseCallback {
    void onCallback(List<DangerCase> dangerCaseList);
}
