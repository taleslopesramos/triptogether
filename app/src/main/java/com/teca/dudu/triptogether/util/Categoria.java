package com.teca.dudu.triptogether.util;

public class Categoria{
    private int numCategoria, iconeCategoria;
    private String nomeCategoria;
    private String secaoCategoria;

    public Categoria(int iconeCategoria, String nomeCategoria, String secaoCategoria) {
        this.iconeCategoria = iconeCategoria;
        this.nomeCategoria = nomeCategoria;
        this.secaoCategoria = secaoCategoria;
    }

    public Categoria(int iconeCategoria, String nomeCategoria,  String secaoCategoria, int numCategoria) {
        this.iconeCategoria = iconeCategoria;
        this.nomeCategoria = nomeCategoria;
        this.numCategoria = numCategoria;
        this.secaoCategoria = secaoCategoria;
    }

    public int getIconeCategoria() {
        return iconeCategoria;
    }

    public void setIconeCategoria(int iconeCategoria) {
        this.iconeCategoria = iconeCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getSecaoCategoria() {
        return secaoCategoria;
    }

    public void setSecaoCategoria(String secaoCategoria) {
        this.secaoCategoria = secaoCategoria;
    }

    public int getNumCategoria() {
        return numCategoria;
    }

    public void setNumCategoria(int numCategoria) {
        this.numCategoria = numCategoria;
    }
}
