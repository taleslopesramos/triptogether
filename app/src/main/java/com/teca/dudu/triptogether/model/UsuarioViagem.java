package com.teca.dudu.triptogether.model;

/**
 * Created by tales on 05/10/16.
 */

public class UsuarioViagem {
    private Integer id_usuario,id_viagem;
    private String data;
    private boolean estaAtiva;

    public UsuarioViagem(Integer id_usuario, Integer id_viagem, boolean estaAtiva) {
        this.id_usuario = id_usuario;
        this.id_viagem = id_viagem;
        this.estaAtiva = estaAtiva;
    }

    public UsuarioViagem(Integer id_usuario, Integer id_viagem, String data) {
        this.id_usuario = id_usuario;
        this.id_viagem = id_viagem;
        this.data = data;
    }

    public boolean estaAtiva() {
        return estaAtiva;
    }

    public void setEstaAtiva(boolean estaAtiva) {
        this.estaAtiva = estaAtiva;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_viagem() {
        return id_viagem;
    }

    public void setId_viagem(Integer id_viagem) {
        this.id_viagem = id_viagem;
    }
}
