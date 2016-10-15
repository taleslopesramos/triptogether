package com.teca.dudu.triptogether.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.ViagensAdapter;
import com.teca.dudu.triptogether.dao.UsuarioViagemDao;
import com.teca.dudu.triptogether.dao.ViagemDao;
import com.teca.dudu.triptogether.model.Viagem;

import java.util.ArrayList;

public class ListaViagensActivity extends ListActivity {
    private ViagemDao viagemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_viagens);
        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.ID_file_key), Context.MODE_PRIVATE);

        int id_usuario = sharedPref.getInt(getString(R.string.ID_file_key),-1);

        if(id_usuario != -1) {
            ArrayList<Viagem> viagens = new ArrayList<Viagem>();
            viagemDao = new ViagemDao(this);
            viagens = viagemDao.listaViagensDeUmUsuario(id_usuario);

            ListView listViagens = (ListView) findViewById(R.id.list_viagem);
            if (viagens.size() >= 1) {
                ViagensAdapter adapter = new ViagensAdapter(this, viagens);
                listViagens.setAdapter(adapter);
            }
        }
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Viagem viagem = (Viagem) this.getListAdapter().getItem(position);


    }
}
