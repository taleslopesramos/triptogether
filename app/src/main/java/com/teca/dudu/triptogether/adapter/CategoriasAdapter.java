package com.teca.dudu.triptogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.util.Categoria;

import java.util.ArrayList;

/**
 * Created by DUDU on 13/10/2016.
 */

public class CategoriasAdapter extends ArrayAdapter<Categoria> {

    public CategoriasAdapter(Context context, ArrayList<Categoria> categorias){
        super(context, 0, categorias);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        boolean showSeparator = false;

        showSeparator = (position) == 0 || position == 4 || position == 9;
        View listViewItem = convertView;
        if(listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_categorias, parent, false);

        Categoria currentCategoria = getItem(position);

        TextView txtNomeCategoria = (TextView) listViewItem.findViewById(R.id.itemcategoria_nome);
        txtNomeCategoria.setText(currentCategoria.getNomeCategoria());

        TextView txtSecaoCategoria = (TextView) listViewItem.findViewById(R.id.itemcategoria_secao);
        txtSecaoCategoria.setText(currentCategoria.getSecaoCategoria());

        ImageView imgCategoria = (ImageView) listViewItem.findViewById(R.id.itemcategoria_img);
        imgCategoria.setImageResource(currentCategoria.getIconeCategoria());

        if(showSeparator)
            txtSecaoCategoria.setVisibility(View.VISIBLE);
        else
            txtSecaoCategoria.setVisibility(View.GONE);

        return listViewItem;

    }
}

