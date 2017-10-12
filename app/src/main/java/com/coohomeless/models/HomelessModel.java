package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class HomelessModel extends Model {

    private Integer idHomeless;
    private String name;
    private String anonymous;
    private String photo;
    private String background;
    private String statusType;
    private date createdAt;
    private date updatedAt;

   public HomelessModel(Integer idHomeless, String name, String anonymous, String photo, String background, String statusType, date createdAt, date updatedAt) {
      this.idHomeless = idHomeless;
      this.name = name;
      this.anonymous = anonymous;
      this.photo = photo;
      this.background = background;
      this.statusType = statusType;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
    }

	public Integer getIdHomeless() {
		return idHomeless;
	}

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
