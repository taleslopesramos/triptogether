package com.teca.dudu.triptogether.util;

import android.content.Context;

import com.teca.dudu.triptogether.R;

import java.util.ArrayList;

/**
 * Created by DUDU on 15/10/2016.
 */

public class InicializaCategorias {
    ArrayList<Categoria> categorias;
    public InicializaCategorias(Context c) {
        categorias = new ArrayList<Categoria>();
        categorias.add(new Categoria(R.drawable.agua72, c.getString(R.string.categorias_0_str), c.getString(R.string.categorias_secao1_str), 0));
        categorias.add(new Categoria(R.drawable.almoco72, c.getString(R.string.categorias_1_str), c.getString(R.string.categorias_secao1_str), 1));
        categorias.add(new Categoria(R.drawable.bebida72, c.getString(R.string.categorias_2_str), c.getString(R.string.categorias_secao1_str), 2));
        categorias.add(new Categoria(R.drawable.jantar72, c.getString(R.string.categorias_3_str), c.getString(R.string.categorias_secao1_str), 3) );
        categorias.add(new Categoria(R.drawable.jogos72, c.getString(R.string.categorias_4_str), c.getString(R.string.categorias_secao2_str), 4));
        categorias.add(new Categoria(R.drawable.book72, c.getString(R.string.categorias_5_str), c.getString(R.string.categorias_secao2_str), 5));
        categorias.add(new Categoria(R.drawable.esporte72, c.getString(R.string.categorias_6_str), c.getString(R.string.categorias_secao2_str), 6));
        categorias.add(new Categoria(R.drawable.tourist72, c.getString(R.string.categorias_7_str), c.getString(R.string.categorias_secao2_str), 7));
        categorias.add(new Categoria(R.drawable.ingresso72, c.getString(R.string.categorias_8_str), c.getString(R.string.categorias_secao2_str), 8));
        categorias.add(new Categoria(R.drawable.aviao72,c.getString( R.string.categorias_9_str), c.getString(R.string.categorias_secao3_str), 9));
        categorias.add(new Categoria(R.drawable.bicicleta72, c.getString(R.string.categorias_10_str), c.getString(R.string.categorias_secao3_str), 10));
        categorias.add(new Categoria(R.drawable.gasolina72, c.getString(R.string.categorias_11_str), c.getString(R.string.categorias_secao3_str), 11));
        categorias.add(new Categoria(R.drawable.trem72, c.getString(R.string.categorias_12_str), c.getString(R.string.categorias_secao3_str), 12));
        categorias.add(new Categoria(R.drawable.onibus72, c.getString(R.string.categorias_13_str), c.getString(R.string.categorias_secao3_str), 13));


    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
}
