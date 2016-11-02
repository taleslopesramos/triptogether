package com.teca.dudu.triptogether.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.util.CircleBitmap;

import java.util.ArrayList;

/**
 * Created by DUDU on 04/10/2016.
 */

public class QuemUsouAdapter extends ArrayAdapter<Usuario>{

    public QuemUsouAdapter(Context context, ArrayList<Usuario> usuarios){
        super(context, 0, usuarios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if(listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item_quemusou, parent, false);

        Usuario currentUsuario = getItem(position);

        TextView txtNomeUsuario = (TextView) listViewItem.findViewById(R.id.itemquemusou_nome);

        txtNomeUsuario.setText(currentUsuario.getNome());


        //TO DO
        //Imagem do usuario


        ImageView imgUsuario = (ImageView) listViewItem.findViewById(R.id.itemquemusou_img);
        if(currentUsuario.getImgPerfil() != null) {
            Bitmap img = BitmapFactory.decodeByteArray(currentUsuario.getImgPerfil(), 0, currentUsuario.getImgPerfil().length); //Transforma o byteArray em bitmap
            CircleBitmap circle = new CircleBitmap();
            if (img != null && imgUsuario != null) { // se nenhum deles for nulo mostra no nav_drawer
                imgUsuario.setImageBitmap(circle.getRoundedShape(img));
            }
        }



        EditText editText = (EditText) listViewItem.findViewById(R.id.itemquemusou_edittxt);

        editText.setText("0");
        return listViewItem;

    }
}
