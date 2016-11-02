package com.teca.dudu.triptogether.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.DespesaDao;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.util.CircleBitmap;
import com.teca.dudu.triptogether.util.RoundImage;

import java.util.ArrayList;

/**
 * Created by DUDU on 29/09/2016.
 */
public class UsuariosAdapter extends ArrayAdapter<Usuario>{
    int id_item;
    public UsuariosAdapter(Context context, ArrayList<Usuario> usuarios){
        super(context, 0, usuarios);
        id_item = 0;
    }

    public UsuariosAdapter(Context context, ArrayList<Usuario> usuarios,int id_item){
        super(context, 0, usuarios);
        this.id_item = id_item;
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

        SharedPreferences sharedPref;

        sharedPref = getContext().getSharedPreferences(
                getContext().getString(R.string.ID_VIAGEM_file_key), Context.MODE_PRIVATE);
        int id_viagem = sharedPref.getInt(getContext().getString(R.string.ID_VIAGEM_file_key),-1);

        DespesaDao despesaDao = new DespesaDao(listViewItem.getContext());
        if(id_item == 0) {
            Float saldoUsuario = despesaDao.valorGastoUsuario(currentUsuario.get_id(), id_viagem);
            txtStatusUsuario.setText(saldoUsuario.toString());
        }
        else{
            Float saldoUsuario = despesaDao.buscarDespesaPorId(id_viagem,id_item,currentUsuario.get_id()).getValorpago();
            txtStatusUsuario.setText(saldoUsuario.toString());
        }
        despesaDao.close();

        ImageView imgUsuario = (ImageView) listViewItem.findViewById(R.id.itemusuario_img);
        if(currentUsuario.getImgPerfil() != null) {
            Bitmap img = BitmapFactory.decodeByteArray(currentUsuario.getImgPerfil(), 0, currentUsuario.getImgPerfil().length); //Transforma o byteArray em bitmap
            CircleBitmap circle = new CircleBitmap();
            RoundImage round = new RoundImage(img);
            if (img != null && imgUsuario != null) { // se nenhum deles for nulo mostra no nav_drawer
                imgUsuario.setImageBitmap(circle.getRoundedShape(img));
            }
        }


        return listViewItem;

    }
}
