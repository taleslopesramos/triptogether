package com.teca.dudu.triptogether.model;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;



import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by tales on 28/08/16.
 */
public class Usuario {
    private Integer _id;
    private Integer idViagem;
    private String nome;
    private String nickname;
    private String email;
    private String senha;
    private byte[] imgPerfil;

    public Usuario(){};

    public Usuario(Integer _id, Integer idViagem, String nome, String nickname, String email, String senha) {
        this._id = _id;
        this.idViagem = idViagem;
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(Integer _id, Integer idViagem, String nome, String nickname, String email, String senha,Blob imgPerfil) throws SQLException {
        this._id = _id;
        this.idViagem = idViagem;
        this.nome = nome;
        this.nickname = nickname;
        this.email = email;

        this.senha = senha;
        int blobLength = (int) imgPerfil.length();
        this.imgPerfil = imgPerfil.getBytes(1,blobLength);
        imgPerfil.free();
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public Integer getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(Integer idViagem) {
        this.idViagem = idViagem;
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

    public void setImgPerfil(Blob imgPerfil) throws SQLException {
        int blobLength = (int) imgPerfil.length();
        this.imgPerfil = imgPerfil.getBytes(1,blobLength);
        imgPerfil.free();
    }
}
