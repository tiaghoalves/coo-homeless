package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class DonationModel extends Model {

    private Integer idDonation;

    public DonationModel(Integer idDonation) {
        this.idDonation = idDonation;
    }

}