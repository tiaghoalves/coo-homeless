package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

import java.util.Calendar;

public class MeetingPointModel extends Model {

    private Integer idMeetingPoint;
    private Calendar createdAt;
    private Calendar updatedAt;

   public MeetingPointModel(Integer idMeetingPoint, Calendar createdAt, Calendar updatedAt) {
      this.idMeetingPoint = idMeetingPoint;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
    }

	public Integer getIdMeetingPoint() {
		return idMeetingPoint;
	}

	public void setIdMeetingPoint(Integer idMeetingPoint) {
		this.idMeetingPoint = idMeetingPoint;
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
