package com.teca.dudu.triptogether.activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.DataBaseHelper;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.model.CurrentUsuario;
import com.teca.dudu.triptogether.model.Usuario;

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

        email = emailEdt.getText().toString();
        senha = senhaEdt.getText().toString();
        Button LoginBtn = (Button) findViewById(R.id.login_btn);
        TextView registerTxtV = (TextView) findViewById(R.id.register_text_link);
        registerTxtV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
       LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaEntradaLogin(v)){
                    int id_usuario = 1;//usuarioDao.loginUsuario(email, senha);
                    if(id_usuario != -1){
                        Intent intentmain = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intentmain);
                    } else {
                        Toast.makeText(v.getContext(), (String)"PREENCHA OS CAMPOS", Toast.LENGTH_SHORT).show();
                    }

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
        if(email != "" || email != null
                || senha != "" || senha != null){
            return true;

        } else {
            Toast.makeText(v.getContext(), (String)"PREENCHA OS CAMPOS", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}
