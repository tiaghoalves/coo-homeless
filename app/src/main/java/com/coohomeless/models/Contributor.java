package com.coohomeless.models;

import com.strongloop.android.loopback.Model;
import com.strongloop.android.remoting.Repository;

import java.util.Map;

/**
 * Created by tiago on 20/09/17.
 */

public class Contributor extends Model {

    private Integer idContributor;
    private String nome;
    private Integer cpf;

    public Contributor(Integer idContributor, String nome, Integer cpf) {
        this.idContributor = idContributor;
        this.nome = nome;
        this.cpf = cpf;
    }


}
