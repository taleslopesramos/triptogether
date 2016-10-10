package com.teca.dudu.triptogether.model;

/**
 * Created by tales on 28/08/16.
 */
public class Viagem {
    private Integer _id;
    private String local;
    private String nome;

    public Viagem(){};

    public Viagem(Integer _id, String local, String nome) {
        this._id = _id;
        this.local = local;
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
