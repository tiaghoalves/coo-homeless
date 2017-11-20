package com.coohomeless.models.meetingPoint;


import com.google.android.gms.maps.model.LatLng;
import com.strongloop.android.loopback.Model;

import java.util.Calendar;

public class MeetingPointContributorModel extends Model {

	private LatLng location;
	private Calendar createdAt;
	private Calendar updatedAt;

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng location) {
		this.location = location;
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
