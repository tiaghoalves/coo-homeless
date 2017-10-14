package com.coohomeless.models.user;

import com.strongloop.android.loopback.Model;

import java.util.Calendar;

public class UserModel extends Model {

    private Integer idUser;
    private String fullName;
    private String login;
    private String email;
    private String password;
    private String urlProfileImage;
    private Calendar createdAt;
    private Calendar updatedAt;


   public UserModel(Integer idUser, String fullName, String login, String email, String password, String urlProfileImage, Calendar createdAt, Calendar updatedAt) {
      this.idUser = idUser;
      this.fullName = fullName;
      this.login = login;
      this.email = email;
      this.password = password;
      this.urlProfileImage = urlProfileImage;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrlProfileImage() {
		return urlProfileImage;
	}

	public void setUrlProfileImage(String urlProfileImage) {
		this.urlProfileImage = urlProfileImage;
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
