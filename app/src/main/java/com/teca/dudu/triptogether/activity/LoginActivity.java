package com.teca.dudu.triptogether.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.UsuarioDao;


public class LoginActivity extends AppCompatActivity{
    EditText emailEdt;
    EditText senhaEdt;
    String email, senha;
    UsuarioDao usuarioDao;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuarioDao = new UsuarioDao(this);

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
                email = emailEdt.getText().toString();
                senha = senhaEdt.getText().toString();
                if(validaEntradaLogin(v)){
                    int id_usuario = usuarioDao.loginUsuario(email, senha);
                    SharedPreferences sharedPref = getSharedPreferences(
                            getString(R.string.ID_file_key),Context.MODE_PRIVATE);
                    SharedPreferences.Editor spEditor = sharedPref.edit();
                    spEditor.putInt(getString(R.string.ID_file_key), id_usuario);
                    spEditor.apply();
                    if( sharedPref.getInt(getString(R.string.ID_file_key),-1) != -1){

                        Intent intentmain = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intentmain);
                    }

                }
                else {
                    Toast.makeText(v.getContext(), (String)"PREENCHA OS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });

        facebookLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentmain);
            }
        });

    }
    private boolean validaEntradaLogin(View v){
        if(email != "" && email != null && senha != "" && senha != null){
            return true;

        } else {
            Toast.makeText(v.getContext(), (String)"PREENCHA OS CAMPOS", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}
