package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class MeetingPointModel extends Model {

    private Integer idMeetingPoint;
    private date createdAt;
    private date updatedAt;

   public HomelessModel(Integer idMeetingPoint, date createdAt, date updatedAt) {
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
