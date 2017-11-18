package com.coohomeless.models.user;

import com.strongloop.android.loopback.Model;

import java.io.Serializable;

public class UserModel extends Model implements Serializable {

    private Integer idUser;
    private String fullName;
	private String email;
    private String urlProfileImage;
	private Integer userType;
    private String createdAt;
    private String updatedAt;

    public UserModel() {
    }

    public UserModel(Integer idUser, String fullName, String email, String urlProfileImage, Integer userType, String createdAt, String updatedAt) {
        this.idUser = idUser;
        this.fullName = fullName;
		this.email = email;
        this.urlProfileImage = urlProfileImage;
        this.userType = userType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

   public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUrlProfileImage() {
		return this.urlProfileImage;
	}

	public void setUrlProfileImage(String urlProfileImage) {
		this.urlProfileImage = urlProfileImage;
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
