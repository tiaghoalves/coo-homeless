package com.coohomeless.models;

import com.strongloop.android.loopback.Model;
import com.strongloop.android.remoting.Repository;

import java.util.Map;

public class ContributorModel extends Model {

    private Integer idContributor;
    private String nome;
    private Integer cpf;

    public ContributorModel(Integer idContributor, String nome, Integer cpf) {
        this.idContributor = idContributor;
        this.nome = nome;
        this.cpf = cpf;
    }


}
