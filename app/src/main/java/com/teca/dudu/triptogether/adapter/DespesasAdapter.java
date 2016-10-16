package com.teca.dudu.triptogether.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.model.Despesa;
import com.teca.dudu.triptogether.model.ItemDespesa;
import com.teca.dudu.triptogether.teste.UsuarioTeste;
import com.teca.dudu.triptogether.util.Categoria;
import com.teca.dudu.triptogether.util.InicializaCategorias;

import java.util.ArrayList;

/**
 * Created by DUDU on 02/10/2016.
 */
public class DespesasAdapter extends ArrayAdapter<ItemDespesa>{

    public DespesasAdapter(Context context, ArrayList<ItemDespesa> despesas){
        super(context, 0, despesas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_despesa, parent, false);

        ArrayList<Categoria> categorias= new InicializaCategorias().getCategorias();
        ItemDespesa currentDespesa = getItem(position);
        Integer indiceCategoria = currentDespesa.getCategoria();

        TextView txtDesc = (TextView) listViewItem.findViewById(R.id.itemdespesa_desc);
        txtDesc.setText(currentDespesa.getDescricao());


        TextView txtCategoriaDespesa = (TextView) listViewItem.findViewById(R.id.itemdespesa_cat);
        txtCategoriaDespesa.setText(categorias.get(indiceCategoria-1).getNomeCategoria());

        TextView txtValor = (TextView)listViewItem.findViewById(R.id.itemdespesa_valor);
        txtValor.setText(currentDespesa.getValor().toString());


        ImageView imgDespesa = (ImageView) listViewItem.findViewById(R.id.itemdespesa_img);
        imgDespesa.setImageResource(categorias.get(indiceCategoria-1).getIconeCategoria());

        return listViewItem;
    }
}

