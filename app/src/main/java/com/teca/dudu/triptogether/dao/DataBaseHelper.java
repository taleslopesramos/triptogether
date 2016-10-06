package com.teca.dudu.triptogether.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tales on 28/08/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    private static final String BANCO_DADOS = "TripTogether";
    private static final int VERSAO = 1;

    private static final String sqlTabelaViagem = "CREATE TABLE \"Viagem\" (" +
            "\"_id\" INTEGER," +
            "\"Local\" TEXT(20) NOT NULL," +
            "PRIMARY KEY (\"_id\")" +
            ");";

    private static final String sqlTabelaUsuario = "CREATE TABLE \"Usuario\" (" +
            "\"_id\" INTEGER," +
          //"\"ID_Viagem\" INTEGER(1)," +
            "\"ImgPerfil\" BLOB(1)," +
            "\"nome\" TEXT(25) NOT NULL," +
            "\"nickname\" TEXT(25) NOT NULL," +
            "\"email\" TEXT(25) NOT NULL," +
            "\"senha\" TEXT(25) NOT NULL," +
            "PRIMARY KEY (\"_id\")" +
            //"CONSTRAINT \"ID_Viagem\" FOREIGN KEY (\"ID_Viagem\") REFERENCES \"Viagem\" (\"_id\") ON DELETE SET NULL" +
            ");";

    private static final String sqlTabelaItemDespesa = "CREATE TABLE \"ItemDespesa\" (" +
            "\"_id\" INTEGER," +
            "\"ID_Viagem\" INTEGER(1)," +
            "\"Moeda\" TEXT(15) NOT NULL," +
            "\"Descricao\" TEXT(15)," +
            "\"Categoria\" TEXT(15) NOT NULL," +
            "\"DataHora\" datetime default CURRENT_TIMESTAMP," +
            "\"Valor\" REAL(1) NOT NULL," +
            "PRIMARY KEY (\"_id\") ," +
            "CONSTRAINT \"fk_ItemDespesa_Despesa_2\" FOREIGN KEY (\"ID_Viagem\") REFERENCES \"Despesa\" (\"ID_Viagem\")" +
            ");";

    private static final String sqlTabelaDespesa = "CREATE TABLE \"Despesa\" (" +
            "\"ValorDevido\" REAL(1) NOT NULL," +
            "\"ValorPago\" REAL(1) NOT NULL," +
            "\"ID_ItemDespesa\" INTEGER," +
            "\"ID_Usuario\" INTEGER," +
            "\"ID_Viagem\" INTEGER," +
            "PRIMARY KEY (\"ID_ItemDespesa\", \"ID_Usuario\", \"ID_Viagem\") " +
            ");";
    private static final String sqlTabelaUsuarioViagem = "CREATE TABLE UsuarioViagem (" +
            "      ID_Usuario INTEGER NOT NULL," +
            "      ID_Viagem INTEGER NOT NULL," +
            "      datafim datetime, " +
            "      estaAtiva INTEGER NOT NULL,"+
            "      PRIMARY KEY (ID_Usuario,ID_Viagem)," +
            "      CONSTRAINT ID_Viagem FOREIGN KEY (ID_Usuario) REFERENCES Usuario (_id)" +
            "      CONSTRAINT ID_Viagem FOREIGN KEY (ID_Viagem)  REFERENCES Viagem  (_id)" +
            ");";

    public DataBaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CRIANDO AS TABELAS
        db.execSQL(sqlTabelaViagem);
        db.execSQL(sqlTabelaUsuario);
        db.execSQL(sqlTabelaItemDespesa);
        db.execSQL(sqlTabelaDespesa);
        db.execSQL(sqlTabelaUsuarioViagem);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Viagem{
        public static final String TABELA = "Viagem";
        public static final String _ID = "_id";
        public static final String LOCAL = "Local";

        public static final String[] COLUNAS = {_ID,LOCAL};
    }

    public static class Usuario{
        public static final String TABELA = "Usuario";
        public static final String _ID = "_id";
       // public static final String ID_VIAGEM = "ID_Viagem";
        public static final String NOME = "nome";
        public static final String NICKNAME = "nickname";
        public static final String EMAIL= "email";
        public static final String SENHA = "senha";
        public static final String IMG_PERFIL = "ImgPerfil";
        public static final String[] COLUNAS = {_ID,NOME,NICKNAME,EMAIL,SENHA,IMG_PERFIL};
    }

    public static class UsuarioViagem{
        public static final String TABELA = "UsuarioViagem";
        public static final String ID_USUARIO = "ID_Usuario";
        public static final String ID_VIAGEM = "ID_Viagem";
        public static final String DATAFIM = "datafim";
        public static final String ESTA_ATIVA = "estaAtiva";
        public static final String[] COLUNAS = {ID_USUARIO,ID_VIAGEM,DATAFIM,ESTA_ATIVA};
    }

    public static class ItemDespesa{
        public static final String TABELA = "ItemDespesa";
        public static final String _ID = "_id";
        public static final String MOEDA = "Moeda";
        public static final String DESCRICAO = "Descricao";
        public static final String CATEGORIA = "Categoria";
        public static final String DATA_HORA = "DataHora";
        public static final String VALOR = "Valor";
        public static final String ID_VIAGEM = "ID_Viagem";

        public static final String[] COLUNAS = {_ID,MOEDA,DESCRICAO,CATEGORIA,DATA_HORA,VALOR,ID_VIAGEM};
    }

    public static class Despesa{
        public static final String TABELA = "Despesa";
        public static final String VALOR_DEVIDO = "ValorDevido";
        public static final String VALOR_PAGO = "ValorPago";
        public static final String ID_ITEMDESPESA = "ID_ItemDespesa";
        public static final String ID_USUARIO = "ID_Usuario";
        public static final String ID_VIAGEM = "ID_Viagem";

        public static final String[] COLUNAS = {VALOR_DEVIDO,VALOR_PAGO,ID_ITEMDESPESA,ID_USUARIO,ID_VIAGEM};
    }
}