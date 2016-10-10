package com.teca.dudu.triptogether.adapter;

/**
 * Created by DUDU on 10/10/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.model.Usuario;

import java.util.ArrayList;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.model.Viagem;
import com.teca.dudu.triptogether.teste.UsuarioTeste;

import java.util.ArrayList;

/**
 * Created by DUDU on 29/09/2016.
 */
public class ViagensAdapter extends ArrayAdapter<Viagem> {

    public ViagensAdapter(Context context, ArrayList<Viagem> viagens){
        super(context, 0, viagens);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_viagens, parent, false);

        Viagem currentViagem = getItem(position);

        TextView txtNomeViagem= (TextView) listViewItem.findViewById(R.id.itemviagem_nome);

        txtNomeViagem.setText(currentViagem.getNome());

        TextView txtLocalViagem = (TextView) listViewItem.findViewById(R.id.itemviagem_local);


        // TO DO
        // quanto o usuário pagou ou deve
        txtLocalViagem.setText("SALDO DO USUARIO");
        //TO DO
        //Imagem do usuario


        return listViewItem;

    }
}
