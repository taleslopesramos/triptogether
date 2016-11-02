package com.teca.dudu.triptogether.model;

/**
 * Created by tales on 28/08/16.
 */
public class Despesa {
    private Float valordevido;
    private Float valorpago;
    private Integer id_itemdespesa;
    private Integer id_usuario;
    private Integer id_viagem;

    public Despesa(){}

    public Despesa(Float valordevido, Float valorpago, Integer id_itemdespesa, Integer idusuario, Integer idviagem) {
        this.valordevido = valordevido;
        this.valorpago = valorpago;
        this.id_itemdespesa = id_itemdespesa;
        this.id_usuario = idusuario;
        this.id_viagem = idviagem;
    }

    public Float getValordevido() {
        return valordevido;
    }

    public void setValordevido(Float valordevido) {
        this.valordevido = valordevido;
    }

    public Float getValorpago() {
        return valorpago;
    }

    public void setValorpago(Float valorpago) {
        this.valorpago = valorpago;
    }

    public Integer getid_itemdespesa() {
        return id_itemdespesa;
    }

    public void setid_itemdespesa(Integer id_itemdespesa) {
        this.id_itemdespesa = id_itemdespesa;
    }

    public Integer getIdusuario() {
        return id_usuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.id_usuario = idusuario;
    }

    public Integer getIdviagem() {
        return id_viagem;
    }

    public void setIdviagem(Integer idviagem) {
        this.id_viagem = idviagem;

    }
}