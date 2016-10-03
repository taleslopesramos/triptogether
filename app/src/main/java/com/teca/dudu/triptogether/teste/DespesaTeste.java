package com.teca.dudu.triptogether.teste;

/**
 * Created by DUDU on 02/10/2016.
 */
public class DespesaTeste {
    private UsuarioTeste usuario;
    private float valorPago;

    public DespesaTeste(UsuarioTeste usuario, float valorPago){
        this.usuario = usuario;
        this.valorPago = valorPago;
    }

    public float getValorPago() {
        return valorPago;
    }

    public UsuarioTeste getUsuario() {
        return usuario;
    }
}
