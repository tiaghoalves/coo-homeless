package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class LocalizationModel extends Model {

    private Integer idLocalization;
    private Double latitude;
    private Double longitude;

    public LocalizationModel(Integer idLocalization, Double latitude, Double longitude) {
        this.idLocalization = idLocalization;
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
