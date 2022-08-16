package com.example.dell.babycare.Activities.developmentActivities.model;

import san.db.handler.SanDbResult;

/**
 * Created by DELL on 1/12/2018.
 */

public class FavoriteList extends SanDbResult<FavoriteList> {


    String FNAME,FDETAIL;

    public FavoriteList( String FNAME, String FDETAIL) {
        this.FNAME = FNAME;
        this.FDETAIL = FDETAIL;

    }

    public String getFNAME() {
        return FNAME;
    }

    public void setFNAME(String FNAME) {
        this.FNAME = FNAME;
    }

    public String getFDETAIL() {
        return FDETAIL;
    }

    public void setFDETAIL(String FDETAIL) {
        this.FDETAIL = FDETAIL;
    }
}
