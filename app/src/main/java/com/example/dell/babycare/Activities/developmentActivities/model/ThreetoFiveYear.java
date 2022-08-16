package com.example.dell.babycare.Activities.developmentActivities.model;

import san.db.handler.SanDbResult;

/**
 * Created by DELL on 1/6/2018.
 */

public class ThreetoFiveYear extends SanDbResult<ThreetoFiveYear> {
    String TNAME,TDETAIL;

    public ThreetoFiveYear() {
    }

    public ThreetoFiveYear(String TNAME, String TDETAIL) {
        this.TNAME = TNAME;
        this.TDETAIL = TDETAIL;
    }

    public String getTNAME() {
        return TNAME;
    }

    public void setTNAME(String TNAME) {
        this.TNAME = TNAME;
    }

    public String getTDETAIL() {
        return TDETAIL;
    }

    public void setTDETAIL(String TDETAIL) {
        this.TDETAIL = TDETAIL;
    }
}
