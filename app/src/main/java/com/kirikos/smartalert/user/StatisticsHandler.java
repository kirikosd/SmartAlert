package com.kirikos.smartalert.user;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.kirikos.smartalert.backend.DangerCase;

import java.util.ArrayList;
import java.util.List;

public class StatisticsHandler {
    SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);

    public void saveStat(DangerCase dc){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(dc);
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }
    public List<DangerCase> retrieveStats(){
        List<DangerCase> dangerCaseList = new ArrayList<>();
        Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        DangerCase obj = gson.fromJson(json, DangerCase.class);
        dangerCaseList.add(obj);
        return dangerCaseList;
    }

}
