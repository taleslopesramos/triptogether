package com.teca.dudu.triptogether.layout;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;

import com.teca.dudu.triptogether.activity.LoginActivity;
import com.teca.dudu.triptogether.dao.ItemDespesaDao;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.model.CurrentUsuario;
import com.teca.dudu.triptogether.model.ItemDespesa;
import com.teca.dudu.triptogether.model.Usuario;

import java.util.ArrayList;

/**
 * Created by DUDU on 20/09/2016.
 */
public class ViagemFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private int id_usuario = 0;
    public ViagemFragment(){}
    public  ViagemFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ViagemFragment fragment = new ViagemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments().getInt(ARG_PAGE);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_viagem, container, false);

        //pega o ID do usuario logado
        SharedPreferences sharedPref = getActivity().getSharedPreferences(getString(R.string.ID_file_key),Context.MODE_PRIVATE);
        id_usuario = sharedPref.getInt(getString(R.string.ID_file_key),-1);

        TextView txt = (TextView)rootView.findViewById(R.id.nome_viagem);

        txt.setText(""+id_usuario);

        UsuarioDao user = new UsuarioDao(rootView.getContext());


        //ADAPTER SET TO LISTVIEW

        //ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, despesas);
        //setListAdapter(adapter);

        return rootView;

    }
}
