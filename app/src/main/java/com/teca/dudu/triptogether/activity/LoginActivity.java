package com.teca.dudu.triptogether.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.dao.UsuarioViagemDao;


public class LoginActivity extends AppCompatActivity{
    EditText emailEdt;
    EditText senhaEdt;
    String email, senha;
    UsuarioDao usuarioDao;
    UsuarioViagemDao usuarioViagemDao;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //ve se tem algum usuario logado e loga imediatamente
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.ID_file_key),Context.MODE_PRIVATE);
        int id_usuario = sharedPref.getInt(getString(R.string.ID_file_key),-1);

        if(id_usuario != -1){
            Intent intentmain = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentmain);
            finish();
        }

        usuarioDao = new UsuarioDao(this);
        usuarioViagemDao = new UsuarioViagemDao(this);

        Button facebookLoginBtn = (Button) findViewById(R.id.logfacebook_btn);
        emailEdt = (EditText) findViewById(R.id.login_email);
        senhaEdt = (EditText) findViewById(R.id.login_senha);

        Button LoginBtn = (Button) findViewById(R.id.login_btn);
        TextView registerTxtV = (TextView) findViewById(R.id.register_text_link);

        registerTxtV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentreg = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentreg);
            }
        });
        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEdt.getText().toString(); //pega os campos de email e senha
                senha = senhaEdt.getText().toString();
                if(validaEntradaLogin(v)){ //valida
                    int id_usuario = usuarioDao.loginUsuario(email, senha); //procura no bd
                    //salva o id do usuario logado
                    SharedPreferences sharedPref = getSharedPreferences(
                            getString(R.string.ID_file_key),Context.MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sharedPref.edit();
                    spEditor.putInt(getString(R.string.ID_file_key), id_usuario);
                    spEditor.apply();

                    if( sharedPref.getInt(getString(R.string.ID_file_key),-1) != -1){//se tiver ocorrido tudo certo nos passos anteriores vai pra main activity
                        int id_viagem = -1;
                        sharedPref = getSharedPreferences(
                                getString(R.string.ID_VIAGEM_file_key),Context.MODE_PRIVATE);
                        spEditor = sharedPref.edit();

                        id_viagem = usuarioViagemDao.buscarIdViagemAtiva(id_usuario);

                        spEditor.putInt(getString(R.string.ID_VIAGEM_file_key), id_viagem);
                        spEditor.apply();

                        Intent intentmain = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intentmain);
                        finish();
                    }
                }
                else {
                    Toast.makeText(v.getContext(), R.string.ada_toast2_str, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private boolean validaEntradaLogin(View v){
        if(email != "" && email != null && senha != "" && senha != null){
            return true;

        } else {
            Toast.makeText(v.getContext(),R.string.ada_toast2_str, Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        usuarioDao.close();
        usuarioViagemDao.close();
    }

}
