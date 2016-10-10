package com.teca.dudu.triptogether.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.UsuariosAdapter;
import com.teca.dudu.triptogether.adapter.ViagensAdapter;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.dao.UsuarioViagemDao;
import com.teca.dudu.triptogether.dao.ViagemDao;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.model.UsuarioViagem;
import com.teca.dudu.triptogether.model.Viagem;

import java.util.ArrayList;

public class CriaViagemActivity extends AppCompatActivity {
    int id_usuario = -1,id_viagem = -1;
    UsuarioDao usuarioDao;
    ViagemDao viagemDao;
    UsuarioViagemDao usuarioViagemDao;
    ArrayList<Viagem> viagens;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_viagem);
        usuarioDao = new UsuarioDao(this);
        viagemDao = new ViagemDao(this);
        usuarioViagemDao = new UsuarioViagemDao(this);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.ID_file_key), Context.MODE_PRIVATE);
        id_usuario = sharedPref.getInt(getString(R.string.ID_file_key),-1);

        /*
        if(id_usuario != -1){//pega a idviagem atual do usuario logado
            id_viagem = usuarioDao.buscarIdViagem(id_usuario);
        }
        */


        ListView listViagens = (ListView) findViewById(R.id.list_usuarios_cviagem);
        viagens = viagemDao.listaViagensDeUmUsuario(id_usuario);
        ViagensAdapter adapter = new ViagensAdapter(this, viagens);
        listViagens.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_usuario_viagem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog viagemDialog = new Dialog(view.getContext());
                EditText localedt = (EditText)findViewById(R.id.local_viagem);
                viagemDialog.setTitle("Nova Viagem");
                viagemDialog.setContentView(R.layout.dialog_addviagem);
                TextView nomeText = (TextView) viagemDialog.findViewById(R.id.dialogtext_nome);
                nomeText.setText("Nome da Viagem:");
                TextView localText = (TextView) viagemDialog.findViewById(R.id.dialogtext_local);
                localText.setText("Local da viagem");
                Button confirmButton = (Button) viagemDialog.findViewById(R.id.dialog_btn);
                confirmButton.setText("CONFIRMAR");
                viagemDialog.show();
                confirmButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nomeViagem = ((EditText) viagemDialog.findViewById(R.id.dialogedit_nome)).getText().toString();
                        String localViagem = ((EditText) viagemDialog.findViewById(R.id.dialogedit_local)).getText().toString();
                        id_viagem = (int) viagemDao.salvarViagem(new Viagem(null,localViagem,nomeViagem));
                        usuarioViagemDao.salvarUsuarioViagem(new UsuarioViagem(id_usuario, id_viagem, true));

                        Intent intent = new Intent(CriaViagemActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });
    }

}
