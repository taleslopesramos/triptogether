package com.teca.dudu.triptogether.model;

/**
 * Created by tales on 28/08/16.
 */
public class Usuario {
    private Integer _id;
    private String nome;
    private String nickname;
    private String email;
    private String senha;
    private byte[] imgPerfil;

    public Usuario(){}

    public Usuario(Integer _id,  String nome, String nickname, String email, String senha) {
        this._id = _id;
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Integer _id, String nome, String nickname, String email, String senha, byte[] imgPerfil){
        this._id = _id;
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
        this.imgPerfil = imgPerfil;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public byte[] getImgPerfil() {
        return imgPerfil;
    }

    public void setImgPerfil(byte[] imgPerfil) {
        this.imgPerfil = imgPerfil;
    }
}
