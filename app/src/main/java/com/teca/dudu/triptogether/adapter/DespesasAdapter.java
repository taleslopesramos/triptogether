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

        ItemDespesa currentDespesa = getItem(position);

        TextView txtDesc = (TextView) listViewItem.findViewById(R.id.itemdespesa_desc);
        txtDesc.setText(currentDespesa.getDescricao());

        TextView txtCategoriaDespesa = (TextView) listViewItem.findViewById(R.id.itemdespesa_cat);
        txtCategoriaDespesa.setText(currentDespesa.getCategoria());

        TextView txtValor = (TextView)listViewItem.findViewById(R.id.itemdespesa_valor);
        txtValor.setText(currentDespesa.getValor().toString());

        // TO DO
        // quanto o usuário pagou ou deve
       // txtStatusUsuario.setText(currentUsuario.getDescricao());
        //TO DO
        //Imagem do usuario

        ImageView imgDespesa = (ImageView) listViewItem.findViewById(R.id.itemdespesa_img);
        imgDespesa.setImageResource(android.R.drawable.ic_lock_silent_mode);



        return listViewItem;

    }
}

