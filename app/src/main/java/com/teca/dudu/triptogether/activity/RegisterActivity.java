package com.teca.dudu.triptogether.activity;

import android.content.Intent;
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
                usuarioDao.salvarUsuario(new Usuario(null, 1, nome.getText().toString(),
                        nick.getText().toString(), email.getText().toString(),
                        senha.getText().toString()));
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

        /*
        try {
            DataBaseHelper db = new DataBaseHelper(this);
            SQLiteDatabase comn =  db.getWritableDatabase();
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("deu bom");
            alert.show();



        } catch (SQLException e){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("deu ruim");
            alert.show();
        }


       /* eduardoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDao novoUsuario = new UsuarioDao(v.getContext());

                if(novoUsuario.buscarUsuarioPorId(1) == null)
                    novoUsuario.salvarUsuario(new Usuario(1, 1, "Eduardo", "dudu", "eduiah@gmail.com", "minhasenha"));

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                startActivity(intent);

            }
        }); */

    }
    public void onClickReg(View v ){

    }
}
