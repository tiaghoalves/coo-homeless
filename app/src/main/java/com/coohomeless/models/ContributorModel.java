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


}
