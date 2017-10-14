package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

import java.util.Calendar;

public class HomelessModel extends Model {

    private Integer idHomeless;
    private String name;
    private String anonymous;
    private String photo;
    private String background;
    private String statusType;
    private Calendar createdAt;
    private Calendar updatedAt;
   
   public HomelessModel(Integer idHomeless, String name, String anonymous, String photo, String background, String statusType, Calendar createdAt, Calendar updatedAt) {
      this.idHomeless = idHomeless;
      this.name = name;
      this.anonymous = anonymous;
      this.photo = photo;
      this.background = background;
      this.statusType = statusType;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
   }

    public Integer getIdHomeless() { return idHomeless; }

    public void setIdHomeless(Integer idHomeless) {
        this.idHomeless = idHomeless;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
        this.anonymous = anonymous;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
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