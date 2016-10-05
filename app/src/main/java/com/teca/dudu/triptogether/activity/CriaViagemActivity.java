package com.teca.dudu.triptogether.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.UsuariosAdapter;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.dao.ViagemDao;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.model.Viagem;

import java.util.ArrayList;

public class CriaViagemActivity extends AppCompatActivity {
    int id_usuario = -1,id_viagem = -1;
    UsuarioDao usuarioDao;
    ViagemDao viagemDao;
    ArrayList<Usuario> usuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_viagem);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.ID_file_key), Context.MODE_PRIVATE);
        id_usuario = sharedPref.getInt(getString(R.string.ID_file_key),-1);

        /*
        if(id_usuario != -1){//pega a idviagem atual do usuario logado
            id_viagem = usuarioDao.buscarIdViagem(id_usuario);
        }
        */




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_usuario_viagem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                EditText localedt = (EditText)findViewById(R.id.local_viagem);
                id_viagem = (int) viagemDao.salvarViagem(new Viagem(null,localedt.getText().toString()));


                ListView listUsuarios = (ListView) findViewById(R.id.list_usuarios_cviagem);
                usuarios = new ArrayList<Usuario>();
                UsuariosAdapter adapter = new UsuariosAdapter(view.getContext(), usuarios);
                listUsuarios.setAdapter(adapter);
            }
        });
    }

}
