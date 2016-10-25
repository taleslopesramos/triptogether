package com.teca.dudu.triptogether.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.ViagensAdapter;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.dao.UsuarioViagemDao;
import com.teca.dudu.triptogether.dao.ViagemDao;
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

        if(id_usuario != -1) {
            ArrayList<Viagem> viagens = new ArrayList<Viagem>();
            viagemDao = new ViagemDao(this);
            viagens = viagemDao.listaViagensDeUmUsuario(id_usuario);

            ListView listViagens = (ListView) findViewById(R.id.list_usuarios_cviagem);
            if (viagens.size() >= 1) {
                ViagensAdapter adapter = new ViagensAdapter(this, viagens);
                listViagens.setAdapter(adapter);

                listViagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        final Viagem viagemSelecionada = (Viagem)adapterView.getItemAtPosition(i);

                        AlertDialog.Builder dialog = new AlertDialog.Builder(CriaViagemActivity.this)
                                .setTitle("Selecione uma opção")
                                .setNegativeButton("Apagar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        usuarioViagemDao.removerUsuarioViagem(id_usuario,viagemSelecionada.get_id());
                                    }
                                })
                                .setPositiveButton("Tornar viagem ativa", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        usuarioViagemDao.updateViagemAtiva(id_usuario,viagemSelecionada.get_id());
                                        recreate();
                                    }
                                });
                        dialog.show();
                    }
                });

            }
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_usuario_viagem);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
              public void onClick(View view) {
                final Dialog viagemDialog = new Dialog(view.getContext());
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

                        SharedPreferences sharedPref = getSharedPreferences(
                                getString(R.string.ID_VIAGEM_file_key), Context.MODE_PRIVATE);

                        SharedPreferences.Editor spEditor = sharedPref.edit();
                        spEditor.putInt(getString(R.string.ID_VIAGEM_file_key), id_viagem);//salva a id_viagem ativa do usuario logado
                        spEditor.apply();

                        Intent intent = new Intent(CriaViagemActivity.this, AddIntegranteActivity.class);
                        startActivity(intent);
                    }
                });


            }
        });
    }


}
