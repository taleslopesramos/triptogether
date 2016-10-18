package com.teca.dudu.triptogether.activity;

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
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.dao.UsuarioViagemDao;
import com.teca.dudu.triptogether.dao.ViagemDao;
import com.teca.dudu.triptogether.model.Usuario;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;


public class RegisterActivity extends AppCompatActivity{
    private UsuarioDao usuarioDao;
    private UsuarioViagemDao usuarioViagemDao;
    private ViagemDao viagemDao;
    EditText nome,nick,email,senha;
    ImageView imageView;
    Bitmap bmp;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usuarioDao = new UsuarioDao(this);
        usuarioViagemDao = new UsuarioViagemDao(this);
        viagemDao = new ViagemDao(this);
        nome = (EditText) findViewById(R.id.nome);
        nick = (EditText) findViewById(R.id.nick);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.password);
        Button btn = (Button) findViewById(R.id.email_register_button);

        bmp = null;
        imageView = (ImageView) findViewById(R.id.ImageViewId);
        TextView LoadImage = (TextView) findViewById(R.id.TextViewImage);
        LoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 0, stream);

                byte[] array = stream.toByteArray();

                long id_usuario = usuarioDao.salvarUsuario(new Usuario(null, nome.getText().toString(),
                        nick.getText().toString(), email.getText().toString(),
                        senha.getText().toString(),array));


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

            ImageView imageView = (ImageView) findViewById(R.id.ImageViewId);

            bmp = null;
            try {
                bmp = getBitmapFromUri(selectedImage);
                imageView.setImageBitmap(bmp);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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

    public void onClickReg(View v ){

    }
}
