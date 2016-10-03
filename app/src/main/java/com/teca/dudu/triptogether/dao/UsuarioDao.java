package com.teca.dudu.triptogether.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.teca.dudu.triptogether.model.Usuario;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by tales on 28/08/16.
 */
public class UsuarioDao {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public UsuarioDao(Context context){
        dataBaseHelper = new DataBaseHelper(context);
    }

    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = dataBaseHelper.getWritableDatabase();
        }

        return database;
    }

    public void close(){
        dataBaseHelper.close();
        database = null;
    }

    private Usuario criaUsuario(Cursor cursor){
        Usuario model = new Usuario(

                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Usuario._ID)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Usuario.ID_VIAGEM)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuario.NOME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuario.NICKNAME)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuario.EMAIL)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Usuario.SENHA))
        );

        return model;
    }

    public ArrayList<Usuario> listaUsuarios(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuario.TABELA,
                DataBaseHelper.Usuario.COLUNAS,null,null,null,null,null);
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        while (cursor.moveToNext()){
            Usuario model = criaUsuario(cursor);
            usuarios.add(model);
        }
        cursor.close();
        return usuarios;
    }

    public ArrayList<Usuario> listaUsuariosDeUmaViagem(int id_viagem){
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuario.TABELA,DataBaseHelper.Usuario.COLUNAS,"ID_Viagem = ?",
                new String[]{Integer.toString(id_viagem)},null,null,null);
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        while (cursor.moveToNext()){
            Usuario model = criaUsuario(cursor);
            usuarios.add(model);
        }
        cursor.close();
        return usuarios;
    }



    public long salvarUsuario(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Usuario.NOME,usuario.getNome());
        valores.put(DataBaseHelper.Usuario.NICKNAME,usuario.getNickname());
        valores.put(DataBaseHelper.Usuario.EMAIL,usuario.getEmail());
        valores.put(DataBaseHelper.Usuario.SENHA,usuario.getSenha());
        valores.put(DataBaseHelper.Usuario.ID_VIAGEM,usuario.getIdViagem());
        valores.put(DataBaseHelper.Usuario.IMG_PERFIL,usuario.getImgPerfil());

        if(usuario.get_id() != null){
            return getDatabase().update(DataBaseHelper.Usuario.TABELA,valores, "_id = ?",
                    new String[]{usuario.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Usuario.TABELA,null,valores);
    }

    public boolean removerUsuario(int id){
        return getDatabase().delete(DataBaseHelper.Usuario.TABELA,"_id = ?",
                new String[]{Integer.toString(id)}) > 0;
    }

    public Usuario buscarUsuarioPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Usuario.TABELA,DataBaseHelper.Usuario.COLUNAS, "_id = ?",
                new String[]{Integer.toString(id)},null,null,null);


        if (cursor.moveToNext()){
            Usuario model = criaUsuario(cursor);
            cursor.close();
            return model;
        }

        return null;
    }
}
