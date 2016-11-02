package com.teca.dudu.triptogether.teste;

import java.util.ArrayList;

/**
 * Created by DUDU on 02/10/2016.
 */
public class ItemDespesaTeste {
    private ArrayList<DespesaTeste> despesaUsuario;
    private float valorDespesa;
    private String categoria;
    private String divisao;

    public ItemDespesaTeste(ArrayList<DespesaTeste> despesaUsuario, float valorDespesa, String categoria, String divisao){
        this.despesaUsuario = despesaUsuario;
        this.valorDespesa = valorDespesa;
        this.categoria = categoria;
        this.divisao = divisao;
    }

}
