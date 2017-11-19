package com.coohomeless.models.contributor;

import com.strongloop.android.loopback.Model;

public class ContributorModel extends Model {

//	private Integer id_contributor;
	private String name;
	private Integer cpf;
	private String email;

//	public Integer getIdContributor() {
//		return id_contributor;
//	}
//
//	public void setIdContributor(Integer id_contributor) {
//		this.id_contributor = id_contributor;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Nome: " + this.getName() + "\nEmail: "+ this.getEmail();
	}
}
