package com.kirikos.smartalert;

public class DangerCasesHandler {
    public void findPotentialDangerCases(){
        // code to read pending cases from database
        // and calculate their severity

        // if they are pushed to the database
        // the path should be 'cases/pending'
    }
    public void pushAcceptedDangerCases(){
        // code to update database with the
        // cases that have been accepted as dangerous
        // by the employee

        // When they are pushed to the database
        // the path should be 'cases/accepted'
    }
}
