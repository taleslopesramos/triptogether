package com.teca.dudu.triptogether.model;

/**
 * Created by tales on 28/08/16.
 */
public class Despesa {
    private Float valordevido;
    private Float valorpago;
    private Integer iddespesa;
    private Integer idusuario;
    private Integer idviagem;

    public Despesa(){};

    public Despesa(Float valordevido, Float valorpago, Integer iddespesa, Integer idusuario, Integer idviagem) {
        this.valordevido = valordevido;
        this.valorpago = valorpago;
        this.iddespesa = iddespesa;
        this.idusuario = idusuario;
        this.idviagem = idviagem;
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

    public Integer getIddespesa() {
        return iddespesa;
    }

    public void setIddespesa(Integer iddespesa) {
        this.iddespesa = iddespesa;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdviagem() {
        return idviagem;
    }

    public void setIdviagem(Integer idviagem) {
        this.idviagem = idviagem;

    }
}
