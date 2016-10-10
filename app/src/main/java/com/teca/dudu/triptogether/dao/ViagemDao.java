package com.teca.dudu.triptogether.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.teca.dudu.triptogether.model.Viagem;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by tales on 28/08/16.
 */
public class ViagemDao {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public ViagemDao(Context context){
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

    private Viagem criaViagem(Cursor cursor){
        Viagem model = new Viagem(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Viagem._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Viagem.LOCAL)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.Viagem.NOME))
        );

        return model;
    }

    public List<Viagem> listaViagens(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Viagem.TABELA,
                                            DataBaseHelper.Viagem.COLUNAS,null,null,null,null,null);
        List<Viagem> viagens = new ArrayList<Viagem>();

        while (cursor.moveToNext()){
            Viagem model = criaViagem(cursor);
            viagens.add(model);
        }
        cursor.close();
        return viagens;
    }

    public ArrayList<Viagem> listaViagensDeUmUsuario(int id_usuario) {
        Cursor cursor = getDatabase().rawQuery("SELECT Viagem._id as _id,Viagem.Local as Local,Viagem.Nome as Nome " +
                "FROM Viagem JOIN UsuarioViagem on Viagem._id = UsuarioViagem.ID_Viagem " +
                "WHERE UsuarioViagem.ID_Usuario = ?;",new String[]{Integer.toString(id_usuario)});

        ArrayList<Viagem> viagens = new ArrayList<Viagem>();

        while (cursor.moveToNext()){
            Viagem model = criaViagem(cursor);
            viagens.add(model);
        }
        cursor.close();
        return viagens;
    }
    public long salvarViagem(Viagem viagem){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Viagem.LOCAL,viagem.getLocal());
        valores.put(DataBaseHelper.Viagem.NOME, viagem.getNome());


        if(viagem.get_id() != null){
            return getDatabase().update(DataBaseHelper.Viagem.TABELA,valores, "_id = ?",
                    new String[]{viagem.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.Viagem.TABELA,null,valores);
    }

    public boolean removerViagem(int id){
        return getDatabase().delete(DataBaseHelper.Viagem.TABELA,"_id = ?",
                new String[]{Integer.toString(id)}) > 0;
    }

    public Viagem buscarViagemPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.Viagem.TABELA,DataBaseHelper.Viagem.COLUNAS, "_id = ?",
                new String[]{Integer.toString(id)},null,null,null);

        if (cursor.moveToNext()){
            Viagem model = criaViagem(cursor);
            cursor.close();
            return model;
        }

        return null;
    }
}
