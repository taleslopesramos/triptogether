package com.teca.dudu.triptogether.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.teca.dudu.triptogether.model.ItemDespesa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tales on 28/08/16.
 */
public class ItemDespesaDao {
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase database;

    public ItemDespesaDao(Context context){
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

    private ItemDespesa criaItemDespesa(Cursor cursor){
        ItemDespesa model = new ItemDespesa(
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.ItemDespesa._ID)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.ItemDespesa.MOEDA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.ItemDespesa.DESCRICAO)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.ItemDespesa.CATEGORIA)),
                cursor.getString(cursor.getColumnIndex(DataBaseHelper.ItemDespesa.DATA_HORA)),
                cursor.getFloat(cursor.getColumnIndex(DataBaseHelper.ItemDespesa.VALOR)),
                cursor.getInt(cursor.getColumnIndex(DataBaseHelper.ItemDespesa.ID_VIAGEM))
        );

        return model;
    }

    public ArrayList<ItemDespesa> listaDespesa(){
        Cursor cursor = getDatabase().query(DataBaseHelper.ItemDespesa.TABELA,
                DataBaseHelper.ItemDespesa.COLUNAS,null,null,null,null,null);
        ArrayList<ItemDespesa> itemDespesas = new ArrayList<ItemDespesa>();

        while (cursor.moveToNext()){
            ItemDespesa model = criaItemDespesa(cursor);
            itemDespesas.add(model);
        }
        cursor.close();
        return itemDespesas;
    }

    public ArrayList<ItemDespesa> listaItensDeUmaViagem(int id_viagem){
        Cursor cursor = getDatabase().query(DataBaseHelper.ItemDespesa.TABELA,
                DataBaseHelper.ItemDespesa.COLUNAS,"ID_Viagem = ?",
                new String[]{Integer.toString(id_viagem)},null,null,null);
        ArrayList<ItemDespesa> itemDespesas = new ArrayList<ItemDespesa>();

        while (cursor.moveToNext()){
            ItemDespesa model = criaItemDespesa(cursor);
            itemDespesas.add(model);
        }
        cursor.close();
        return itemDespesas;
    }

    public long salvarItemDespesa(ItemDespesa itemDespesa){
        ContentValues valores = new ContentValues();
        valores.put(DataBaseHelper.ItemDespesa.CATEGORIA,itemDespesa.getCategoria());
        valores.put(DataBaseHelper.ItemDespesa.DATA_HORA,itemDespesa.getDataHora());
        valores.put(DataBaseHelper.ItemDespesa.DESCRICAO,itemDespesa.getDescricao());
        valores.put(DataBaseHelper.ItemDespesa.MOEDA,itemDespesa.getMoeda());
        valores.put(DataBaseHelper.ItemDespesa.VALOR,itemDespesa.getValor());
        valores.put(DataBaseHelper.ItemDespesa.ID_VIAGEM,itemDespesa.getIdviagem());

        if(itemDespesa.get_id() != null){
            return getDatabase().update(DataBaseHelper.Despesa.TABELA,valores, "_id = ?",
                    new String[]{itemDespesa.get_id().toString()});
        }
        return getDatabase().insert(DataBaseHelper.ItemDespesa.TABELA,null,valores);
    }

    public boolean removerItemDespesa(int id){
        return getDatabase().delete(DataBaseHelper.ItemDespesa.TABELA,"_id = ?",
                new String[]{Integer.toString(id)}) > 0;
    }

    public ItemDespesa buscarItemDespesaPorId(int id){
        Cursor cursor = getDatabase().query(DataBaseHelper.ItemDespesa.TABELA,DataBaseHelper.ItemDespesa.COLUNAS, "_id = ?",
                new String[]{Integer.toString(id)},null,null,null);

        if (cursor.moveToNext()){
            ItemDespesa model = criaItemDespesa(cursor);
            cursor.close();
            return model;
        }

        return null;
    }
}
