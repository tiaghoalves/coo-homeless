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

}