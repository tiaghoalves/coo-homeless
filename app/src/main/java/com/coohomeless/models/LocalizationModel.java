package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class LocalizationModel extends Model {

    private Integer idLocalization;
    private Double latitude;
    private Double longitude;
    private date createdAt;
    private date updatedAt;

    public LocalizationModel(Integer idLocalization, Double latitude, Double longitude, date createdAt, date updatedAt) {
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

	public date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(date createdAt) {
		this.createdAt = createdAt;
	}

	public date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
