package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class DonationStatusModel extends Model {

    private Integer idDonationStatus;
    private String type;

    public DonationStatusModel(Integer idDonationStatus, String type) {
        this.idDonationStatus = idDonationStatus;
        this.type = type;
    }

}