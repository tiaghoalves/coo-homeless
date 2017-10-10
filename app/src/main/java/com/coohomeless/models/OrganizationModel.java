package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class OrganizationModel extends Model {

    private Integer idOrganization;
    private String name;
    private String descOrganization;
    private Integer cnpj;

    public OrganizationModel(Integer idOrganization, String name, String descOrganization, Integer cpf) {
        this.idOrganization = idOrganization;
        this.name = name;
        this.descOrganization = descOrganization;
        this.cnpj = cnpj;
    }


}