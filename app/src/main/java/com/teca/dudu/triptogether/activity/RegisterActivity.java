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

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.model.Usuario;


public class RegisterActivity extends AppCompatActivity{
    private UsuarioDao usuarioDao;
    EditText nome,nick,email,senha;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usuarioDao = new UsuarioDao(this);

        nome = (EditText) findViewById(R.id.nome);
        nick = (EditText) findViewById(R.id.nick);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.password);
        Button btn = (Button) findViewById(R.id.email_register_button);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long id_usuario = usuarioDao.salvarUsuario(new Usuario(null, 2, nome.getText().toString(),
                        nick.getText().toString(), email.getText().toString(),
                        senha.getText().toString()));

                SharedPreferences sharedPref = getSharedPreferences(
                        getString(R.string.ID_file_key), Context.MODE_PRIVATE);

                SharedPreferences.Editor spEditor = sharedPref.edit();
                spEditor.putInt(getString(R.string.ID_file_key), (int)id_usuario);
                spEditor.apply();

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void onClickReg(View v ){

    }
}
