package com.teca.dudu.triptogether.util;

import com.teca.dudu.triptogether.R;

import java.util.ArrayList;

/**
 * Created by DUDU on 15/10/2016.
 */

public class InicializaCategorias {
    ArrayList<Categoria> categorias;
    public InicializaCategorias() {
        categorias = new ArrayList<Categoria>();
        categorias.add(new Categoria(R.drawable.agua72, "Água", "Comida e Bebida", 0));
        categorias.add(new Categoria(R.drawable.almoco72, "Almoço", "Comida e Bebida", 1));
        categorias.add(new Categoria(R.drawable.bebida72, "Bebida", "Comida e Bebida", 2));
        categorias.add(new Categoria(R.drawable.jantar72, "Jantar", "Comida e Bebida", 3) );
        categorias.add(new Categoria(R.drawable.jogos72, "Jogos Eletrônicos", "Entretenimento", 4));
        categorias.add(new Categoria(R.drawable.book72, "Livros", "Entretenimento", 5));
        categorias.add(new Categoria(R.drawable.esporte72, "Esportes", "Entretenimento", 6));
        categorias.add(new Categoria(R.drawable.tourist72, "Turismo", "Entretenimento", 7));
        categorias.add(new Categoria(R.drawable.ingresso72, "Ingressos", "Entretenimento", 8));
        categorias.add(new Categoria(R.drawable.aviao72, "Avião", "Transporte", 9));
        categorias.add(new Categoria(R.drawable.bicicleta72, "Bicicleta", "Transporte", 10));
        categorias.add(new Categoria(R.drawable.gasolina72, "Gasolina", "Transporte", 11));
        categorias.add(new Categoria(R.drawable.trem72, "Trem", "Transporte", 12));
        categorias.add(new Categoria(R.drawable.onibus72, "Onibus", "Transporte", 13));


    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
}
