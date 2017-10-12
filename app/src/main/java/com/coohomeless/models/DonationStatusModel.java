package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class DonationStatusModel extends Model {

    private Integer idDonationStatus;
    private String type;

    public DonationStatusModel(Integer idDonationStatus, String type) {
        this.idDonationStatus = idDonationStatus;
        this.type = type;
    }

	public Integer getIdDonationStatus() {
		return idDonationStatus;
	}

	public void setIdDonationStatus(Integer idDonationStatus) {
		this.idDonationStatus = idDonationStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
