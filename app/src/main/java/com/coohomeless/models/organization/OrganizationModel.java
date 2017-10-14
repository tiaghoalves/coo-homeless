package com.coohomeless.models.organization;

import com.strongloop.android.loopback.Model;

public class OrganizationModel extends Model {

    private Integer idOrganization;
    private String name;
    private String descOrganization;
    private Integer cnpj;

    public OrganizationModel(Integer idOrganization, String name, String descOrganization, Integer cnpj) {
        this.idOrganization = idOrganization;
        this.name = name;
        this.descOrganization = descOrganization;
		this.cnpj = cnpj;
	}

	public Integer getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(Integer idOrganization) {
		this.idOrganization = idOrganization;
	}

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
}
