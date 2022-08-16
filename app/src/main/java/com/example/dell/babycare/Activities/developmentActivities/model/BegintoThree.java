package com.example.dell.babycare.Activities.developmentActivities.model;

import san.db.handler.SanDbResult;

/**
 * Created by DELL on 1/5/2018.
 */

public class BegintoThree extends SanDbResult<BegintoThree> {
    String BNAME,BDETAIL;

    public BegintoThree() {
    }

    public BegintoThree(String BNAME, String BDETAIL) {
        this.BNAME = BNAME;
        this.BDETAIL = BDETAIL;
    }

    public String getBNAME() {
        return BNAME;
    }

    public void setBNAME(String BNAME) {
        this.BNAME = BNAME;
    }

    public String getBDETAIL() {
        return BDETAIL;
    }

    public void setBDETAIL(String BDETAIL) {
        this.BDETAIL = BDETAIL;
    }
}
