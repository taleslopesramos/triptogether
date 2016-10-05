package com.teca.dudu.triptogether.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.model.UsuarioViagem;
import com.teca.dudu.triptogether.model.Viagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tales on 05/10/16.
 */

public class UsuarioViagemDao {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public UsuarioViagemDao(Context context){
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

    private UsuarioViagem criaUsuarioViagem(Cursor cursor){
        UsuarioViagem model = new UsuarioViagem(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.UsuarioViagem.ID_VIAGEM)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.UsuarioViagem.ID_USUARIO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.UsuarioViagem.ESTA_ATIVA))!=0
                );

        return model;
    }

    public ArrayList<UsuarioViagem> listaUsuarioViagens(){
        Cursor cursor = getDatabase().query(DataBaseHelper.UsuarioViagem.TABELA,
                DataBaseHelper.UsuarioViagem.COLUNAS,null,null,null,null,null);
        ArrayList<UsuarioViagem> usuarioViagens = new ArrayList<UsuarioViagem>();

        while (cursor.moveToNext()){
            UsuarioViagem model = criaUsuarioViagem(cursor);
            usuarioViagens.add(model);
        }
        cursor.close();
        return usuarioViagens;
    }

    public long salvarUsuarioViagem(UsuarioViagem usuarioViagem){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.UsuarioViagem.ID_USUARIO,usuarioViagem.getId_usuario());
        valores.put(DataBaseHelper.UsuarioViagem.ID_VIAGEM,usuarioViagem.getId_viagem());
        valores.put(DataBaseHelper.UsuarioViagem.DATAFIM,usuarioViagem.getData());
        valores.put(DataBaseHelper.UsuarioViagem.ESTA_ATIVA, (usuarioViagem.estaAtiva()) ? 1 : 0);

        return getDatabase().insert(DataBaseHelper.Viagem.TABELA,null,valores);
    }

    public boolean removerUsuarioViagem(int id_usuario,int id_viagem){
        return getDatabase().delete(DataBaseHelper.Viagem.TABELA,"ID_Viagem = ? AND ID_Usuario = ?",
                new String[]{Integer.toString(id_viagem),Integer.toString(id_usuario)}) > 0;
    }

    public UsuarioViagem buscarUsuarioViagemPorId(int id_viagem){
        Cursor cursor = getDatabase().query(DataBaseHelper.Viagem.TABELA,DataBaseHelper.Viagem.COLUNAS,
                "ID_Viagem = ?", new String[]{Integer.toString(id_viagem)},null,null,null);

        if (cursor.moveToNext()){
            UsuarioViagem model = criaUsuarioViagem(cursor);
            cursor.close();
            return model;
        }

        return null;
    }
}