package com.coohomeless.models.organization;

import com.strongloop.android.loopback.Model;

public class OrganizationModel extends Model {

	private String name;
	private String descOrganization;
	private Integer cnpj;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescOrganization() {
		return descOrganization;
	}

	public void setDescOrganization(String descOrganization) {
		this.descOrganization = descOrganization;
	}

	public Integer getCnpj() {
		return cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Nome: " + this.getName() + "\nDescOrganization: "+ this.getDescOrganization() + "\nEmail: "+ this.getEmail();
	}
}
