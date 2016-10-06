package com.teca.dudu.triptogether.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.teca.dudu.triptogether.model.Despesa;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tales on 28/08/16.
 */
public class DespesaDao {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public DespesaDao(Context context){
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

    private Despesa criaDespesa(Cursor cursor){
        Despesa model = new Despesa(
                cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.Despesa.VALOR_DEVIDO)),
                cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.Despesa.VALOR_PAGO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Despesa.ID_ITEMDESPESA)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Despesa.ID_USUARIO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Despesa.ID_VIAGEM))
        );

        return model;
    }

    public ArrayList<Despesa> listaDespesa(){
        Cursor cursor = getDatabase().query(DataBaseHelper.Despesa.TABELA,
                DataBaseHelper.Despesa.COLUNAS,null,null,null,null,null);
        ArrayList<Despesa> despesas = new ArrayList<Despesa>();

        while (cursor.moveToNext()){
            Despesa model = criaDespesa(cursor);
            despesas.add(model);
        }
        cursor.close();
        return despesas;
    }

    public float saldoDoUsuario(int id_usuario,int id_viagem){
        float total = 0;
        Cursor cursor = getDatabase().query(DataBaseHelper.Despesa.TABELA,DataBaseHelper.Despesa.COLUNAS,
                "ID_Usuario = ? and ID_Viagem = ?",new String[]{Integer.toString(id_usuario),
                        Integer.toString(id_viagem)},null,null,null);

        while (cursor.moveToNext()){
            Despesa model = criaDespesa(cursor);
            total -= model.getValordevido();
            total += model.getValorpago();
        }

        cursor.close();
        return total;
    }

    public ArrayList<Despesa> listaDespesasDeUmItem(int id_item,int id_viagem){
        Cursor cursor = getDatabase().query(DataBaseHelper.Despesa.TABELA,
                DataBaseHelper.Despesa.COLUNAS,"? = ? AND ? = ?",
                new String[]{DataBaseHelper.Despesa.ID_ITEMDESPESA,Integer.toString(id_item),
                        DataBaseHelper.Despesa.ID_VIAGEM,Integer.toString(id_viagem)},null,null,null);
        ArrayList<Despesa> despesas = new ArrayList<Despesa>();

        while (cursor.moveToNext()){
            Despesa model = criaDespesa(cursor);
            despesas.add(model);
        }
        cursor.close();
        return despesas;
    }

    public long salvarDespesa(Despesa despesa){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.Despesa.ID_ITEMDESPESA,despesa.getid_itemdespesa());
        valores.put(DataBaseHelper.Despesa.ID_USUARIO,despesa.getIdusuario());
        valores.put(DataBaseHelper.Despesa.ID_VIAGEM,despesa.getIdviagem());
        valores.put(DataBaseHelper.Despesa.VALOR_DEVIDO,despesa.getValordevido());
        valores.put(DataBaseHelper.Despesa.VALOR_PAGO,despesa.getValorpago());

        /*if(despesa.getIddespesa() != null && despesa.getIdusuario() != null && despesa.getIdviagem() != null){
            return getDatabase().update(DataBaseHelper.Despesa.TABELA,valores, "_id = ?",
                    new String[]{despesa.getIdusuario().toString(),despesa.getIdviagem().toString(),
                            despesa.getIddespesa().toString()});
        }*/
        return getDatabase().insert(DataBaseHelper.Despesa.TABELA,null,valores);
    }

    public boolean removerLancamento(int idDespesa,int idViagem,int idUsuario){
        return getDatabase().delete(DataBaseHelper.Despesa.TABELA,"_id = ?",
                new String[]{Integer.toString(idViagem),Integer.toString(idDespesa),Integer.toString(idUsuario)}) > 0;
    }

    public Despesa buscarDespesaPorId(int idDespesa,int idViagem,int idUsuario){
        Cursor cursor = getDatabase().query(DataBaseHelper.Despesa.TABELA,DataBaseHelper.Despesa.COLUNAS, "_id = ?",
                new String[]{Integer.toString(idDespesa),Integer.toString(idViagem),Integer.toString(idUsuario)},
                null,null,null);
        if (cursor.moveToNext()){
            Despesa model = criaDespesa(cursor);
            cursor.close();
            return model;
        }

        return null;
    }
}