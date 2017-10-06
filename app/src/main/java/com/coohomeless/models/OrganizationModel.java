package com.coohomeless.models;

import com.strongloop.android.loopback.Model;

public class OrganizationModel extends Model {

    private Integer idOrganization;
    private String nome;
    private String descOrganization;
    private Integer cnpj;

    public OrganizationModel(Integer idOrganization, String nome, String descOrganization, Integer cpf) {
        this.idOrganization = idOrganization;
        this.nome = nome;
        this.descOrganization = descOrganization;
        this.cnpj = cnpj;
    }


}