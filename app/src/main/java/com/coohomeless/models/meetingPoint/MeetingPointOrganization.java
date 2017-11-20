package com.coohomeless.models.meetingPoint;

import com.google.android.gms.maps.model.LatLng;
import com.strongloop.android.loopback.Model;

public class MeetingPointOrganization extends Model {

    private LatLng location;
    private String createdAt;
    private String updatedAt;

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
