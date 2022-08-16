package com.example.dell.babycare.Activities.developmentActivities.model;

import san.db.handler.SanDbResult;

/**
 * Created by DELL on 1/9/2018.
 */

public class SixtoTenMonth extends SanDbResult<SixtoTenMonth> {
    String SNAME,SDETAIL;

    public SixtoTenMonth() {
    }


    public SixtoTenMonth(String SNAME, String SDETAIL) {
        this.SNAME = SNAME;
        this.SDETAIL = SDETAIL;
    }

    public String getSNAME() {
        return SNAME;
    }

    public void setSNAME(String SNAME) {
        this.SNAME = SNAME;
    }

    public String getSDETAIL() {
        return SDETAIL;
    }

    public void setSDETAIL(String SDETAIL) {
        this.SDETAIL = SDETAIL;
    }
}
