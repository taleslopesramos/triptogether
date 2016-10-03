package com.teca.dudu.triptogether.activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.DataBaseHelper;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.model.Usuario;

public class LoginActivity extends AppCompatActivity{
    DataBaseHelper myDb;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button RegisterBtn = (Button) findViewById(R.id.buttonLogin);
        Button EnterBtn = (Button) findViewById(R.id.enter);
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmain = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intentmain);
            }
        });

        EnterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmain = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentmain);
            }
        });

    }
    public void onClickEduardo(View v ){

    }
}
