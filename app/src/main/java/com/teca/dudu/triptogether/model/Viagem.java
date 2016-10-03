package com.teca.dudu.triptogether.model;

/**
 * Created by tales on 28/08/16.
 */
public class Viagem {
    private Integer _id;
    private String local;

    public Viagem(){};

    public Viagem(Integer _id, String local) {
        this._id = _id;
        this.local = local;
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

}
