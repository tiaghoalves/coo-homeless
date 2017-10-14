package com.coohomeless.models.localization;

import com.strongloop.android.loopback.Model;

import java.util.Calendar;

public class LocalizationModel extends Model {

    private Integer idLocalization;
    private Double latitude;
    private Double longitude;
    private Calendar createdAt;
    private Calendar updatedAt;

    public LocalizationModel(Integer idLocalization, Double latitude, Double longitude, Calendar createdAt, Calendar updatedAt) {
        this.idLocalization = idLocalization;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
     	this.updatedAt = updatedAt;
    }

	public Integer getIdLocalization() {
		return idLocalization;
	}

	public void setIdLocalization(Integer idLocalization) {
		this.idLocalization = idLocalization;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}

	public Calendar getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Calendar updatedAt) {
		this.updatedAt = updatedAt;
	}
}
