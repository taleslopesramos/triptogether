package com.teca.dudu.triptogether.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.util.CircleBitmap;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

public class PerfilActivity extends AppCompatActivity {
    ImageView imgPerfil;
    EditText nomeEdt,nickEdt,emailEdt,senhaEdt;
    UsuarioDao usuarioDao;
    Usuario user;
    int id_usuario;
    boolean editando;
    Bitmap bmp;
    byte[] array;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editando = false;
        imgPerfil = (ImageView) findViewById(R.id.imgPerfil);
        nomeEdt = (EditText) findViewById(R.id.nome_tv);
        nickEdt = (EditText) findViewById(R.id.nick_tv);
        emailEdt = (EditText) findViewById(R.id.email_tv);
        senhaEdt = (EditText) findViewById(R.id.senha_tv);

        usuarioDao = new UsuarioDao(this);
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.ID_file_key), Context.MODE_PRIVATE);
        id_usuario = sharedPref.getInt(getString(R.string.ID_file_key),-1);

        user = usuarioDao.buscarUsuarioPorId(id_usuario);

        nomeEdt.setText(user.getNome());
        nickEdt.setText(user.getNickname());
        emailEdt.setText(user.getEmail());
        senhaEdt.setText("******");
        if(user.getImgPerfil() != null) {
            bmp = BitmapFactory.decodeByteArray(user.getImgPerfil(), 0, user.getImgPerfil().length); //Transforma o byteArray em bitmap
            CircleBitmap circle = new CircleBitmap();
            if (bmp != null && imgPerfil != null) {
                imgPerfil.setImageBitmap(circle.getRoundedShape(bmp));
            }
        }
    }

    public void mudaPerfil(View view){
        final Dialog viagemDialog = new Dialog(this);
        viagemDialog.setTitle("Confirmando Mudança");
        viagemDialog.setContentView(R.layout.dialog_confirmasenha);
        Button confirmButton = (Button) viagemDialog.findViewById(R.id.dialog_btn);
        viagemDialog.show();
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText senha1 = (EditText) viagemDialog.findViewById(R.id.dialogedit_senha1);
                EditText senha2 = (EditText) viagemDialog.findViewById(R.id.dialogedit_senha2);

                if(senha1.getText().toString().equals( senha2.getText().toString() )){
                    String senha = senha1.getText().toString();
                    boolean certo = (usuarioDao.loginUsuario(user.getEmail(),senha) != -1);

                    if(certo){
                        user.setNome(nomeEdt.getText().toString());
                        user.setNickname(nickEdt.getText().toString());
                        user.setEmail(emailEdt.getText().toString());
                        user.setSenha(senhaEdt.getText().toString());
                        user.setImgPerfil(array);
                        if(usuarioDao.salvarUsuario(user)!=-1){
                            Snackbar.make(v, "Perfil do Usuario Modificado!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        else {
                            Snackbar.make(v, "Erro ao Modificar o Perfil!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }
                    else {
                        Snackbar.make(v, "Senha errada!", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
                else{
                    Snackbar.make(v, "Senhas Diferentes!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
                user = usuarioDao.buscarUsuarioPorId(id_usuario);

                nomeEdt.setText(user.getNome());
                nickEdt.setText(user.getNickname());
                emailEdt.setText(user.getEmail());
                senhaEdt.setText("******");
                nomeEdt.setEnabled(false);
                nickEdt.setEnabled(false);
                emailEdt.setEnabled(false);
                senhaEdt.setEnabled(false);

                if(user.getImgPerfil() != null) {
                    bmp = BitmapFactory.decodeByteArray(user.getImgPerfil(), 0, user.getImgPerfil().length); //Transforma o byteArray em bitmap

                    if (bmp != null && imgPerfil != null) {
                        imgPerfil.setImageBitmap(bmp);
                    }
                }
                editando = false;
            }
        });
    }

    public void editar(View view){
        if(!editando) {
            nomeEdt.setEnabled(true);
            nickEdt.setEnabled(true);
            emailEdt.setEnabled(true);
            senhaEdt.setEnabled(true);
            nomeEdt.setText("");
            nickEdt.setText("");
            emailEdt.setText("");
            senhaEdt.setText("");
            editando = true;
        }
        else{
            nomeEdt.setText(user.getNome());
            nickEdt.setText(user.getNickname());
            emailEdt.setText(user.getEmail());
            senhaEdt.setText("******");
            if(user.getImgPerfil() != null) {
                bmp = BitmapFactory.decodeByteArray(user.getImgPerfil(), 0, user.getImgPerfil().length); //Transforma o byteArray em bitmap

                if (bmp != null && imgPerfil != null) {
                    imgPerfil.setImageBitmap(bmp);
                }
            }
            nomeEdt.setEnabled(false);
            nickEdt.setEnabled(false);
            emailEdt.setEnabled(false);
            senhaEdt.setEnabled(false);
            editando = false;
        }
    }

    public void edtImage(View view){
        if(editando) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();



            bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(bmp!=null) {
                imgPerfil.setImageBitmap(bmp);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 0, stream);

                array = stream.toByteArray();
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();

        return image;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
