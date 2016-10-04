package com.teca.dudu.triptogether.model;

/**
 * Created by DUDU on 03/10/2016.
 */
public class CurrentUsuario {

    private static int id_currentUsuario = -1;

    private CurrentUsuario(int id){
        id_currentUsuario = id;
    }

    public static synchronized int getInstance(int id){
        if (id_currentUsuario == -1){
            id_currentUsuario = id;
        }

        return  id_currentUsuario;
    }

}
