package com.teca.dudu.triptogether.teste;

/**
 * Created by DUDU on 02/10/2016.
 */
public class UsuarioTeste {
    private String nome;
    private String descricao;
    private int icon = 0;

    public UsuarioTeste(String nome, String descricao, int icon){
        this.nome = nome;
        this.descricao = descricao;
        this.icon = icon;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNome() {
        return nome;
    }

    public int getIconId() {
        return icon;
    }
}
