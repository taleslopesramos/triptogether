package com.teca.dudu.triptogether.teste;

import com.teca.dudu.triptogether.model.ItemDespesa;
import com.teca.dudu.triptogether.model.Usuario;

import java.util.ArrayList;

/**
 * Created by DUDU on 02/10/2016.
 */
public class ViagemTeste {
    private ArrayList<UsuarioTeste> usuarios;
    private ArrayList<ItemDespesaTeste> itensDespesa;

    public ViagemTeste() {
        usuarios=new ArrayList<UsuarioTeste>();
        itensDespesa=new ArrayList<ItemDespesaTeste>();

        usuarios.add(new UsuarioTeste("Eduardo", "sei la", android.R.drawable.ic_input_add));
        usuarios.add(new UsuarioTeste("Tales", "loko", android.R.drawable.ic_input_add));
        usuarios.add(new UsuarioTeste("Camila", "chata", android.R.drawable.ic_input_add));
    }

    public ArrayList<UsuarioTeste> getUsuarios() {
        return usuarios;
    }

    public ArrayList<ItemDespesaTeste> getItensDespesa() {
        return itensDespesa;
    }
}
