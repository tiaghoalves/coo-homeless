package com.coohomeless.models.homeless;


import com.strongloop.android.loopback.Model;

public class HomelessInfoModel extends Model {

    private Integer idHomelessInfo;

    public HomelessInfoModel(Integer idHomelessInfo){
        this.idHomelessInfo = idHomelessInfo;
    }
}
