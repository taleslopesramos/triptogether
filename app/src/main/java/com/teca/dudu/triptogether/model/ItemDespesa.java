package com.teca.dudu.triptogether.model;

/**
 * Created by tales on 28/08/16.
 */
public class ItemDespesa {
    private Integer _id;
    private String moeda;
    private String descricao;
    private String categoria;

    public ItemDespesa(){};

    public ItemDespesa(Integer _id, String moeda, String descricao, String categoria,
                   String dataHora, Float valor, Integer idviagem) {
        this._id = _id;
        this.moeda = moeda;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataHora = dataHora;
        this.valor = valor;
        this.idviagem = idviagem;
    }

    private String dataHora;
    private Float valor;
    private Integer idviagem;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Integer getIdviagem() {
        return idviagem;
    }

    public void setIdviagem(Integer idviagem) {
        this.idviagem = idviagem;
    }
}
