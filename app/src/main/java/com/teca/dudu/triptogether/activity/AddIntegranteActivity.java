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

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.dao.UsuarioViagemDao;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.model.UsuarioViagem;

import java.util.ArrayList;

public class AddIntegranteActivity extends AppCompatActivity {
    String email;
    UsuarioDao usuarioDao;
    UsuarioViagemDao usuarioViagemDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_integrante);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usuarioDao = new UsuarioDao(this);
        usuarioViagemDao = new UsuarioViagemDao(this);

        FloatingActionButton addIntegrantebtn = (FloatingActionButton) findViewById(R.id.add_integrante_btn);
        addIntegrantebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText integranteEdt = (EditText) findViewById(R.id.add_integrante_edt);
                email = integranteEdt.getText().toString();
                Usuario user = usuarioDao.buscarUsuarioPorEmail(email);

                if(user != null){//se o email do usuário existir..
                    //torna a viagem ativa do usuario desativa e o coloca ele na nova

                    if(usuarioViagemDao.buscarIdViagemAtiva(user.get_id()) != -1){
                        usuarioViagemDao.setViagemAtiva(user.get_id(),false);
                    }

                    SharedPreferences sharedPref = getSharedPreferences(
                            getString(R.string.ID_VIAGEM_file_key), Context.MODE_PRIVATE);
                    int id_viagem = sharedPref.getInt(getString(R.string.ID_VIAGEM_file_key),-1);

                    usuarioViagemDao.salvarUsuarioViagem(new UsuarioViagem(user.get_id(),id_viagem,true));
                    Snackbar.make(view, "Usuario Adicionado a viagem!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{//se o email não existir
                    Snackbar.make(view, "Ops! Esse Email é inválido", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

}
