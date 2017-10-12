package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class ContributorModel extends Model {

    private Integer idContributor;
    private String name;
    private Integer cpf;

    public ContributorModel(Integer idContributor, String name, Integer cpf) {
        this.idContributor = idContributor;
        this.name = name;
        this.cpf = cpf;
    }

	public Integer getIdContributor() {
		return idContributor;
	}

	public void setIdContributor(Integer idContributor) {
		this.idContributor = idContributor;
	}

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

}
