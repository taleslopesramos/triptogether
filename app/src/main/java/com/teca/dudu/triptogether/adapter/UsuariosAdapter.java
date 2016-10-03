package com.teca.dudu.triptogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.teste.UsuarioTeste;

import java.util.ArrayList;

/**
 * Created by DUDU on 29/09/2016.
 */
public class UsuariosAdapter extends ArrayAdapter<Usuario>{

    public UsuariosAdapter(Context context, ArrayList<Usuario> usuarios){
        super(context, 0, usuarios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_usuario, parent, false);

        Usuario currentUsuario = getItem(position);

        TextView txtNomeUsuario = (TextView) listViewItem.findViewById(R.id.itemusuario_nome);

        txtNomeUsuario.setText(currentUsuario.getNome());

        TextView txtStatusUsuario = (TextView) listViewItem.findViewById(R.id.itemusuario_desc);


        // TO DO
        // quanto o usuário pagou ou deve
        txtStatusUsuario.setText("SALDO DO USUARIO");
        //TO DO
        //Imagem do usuario

        ImageView imgUsuario = (ImageView) listViewItem.findViewById(R.id.itemusuario_img);

        imgUsuario.setImageResource(android.R.drawable.ic_dialog_map);


        return listViewItem;

    }
}
