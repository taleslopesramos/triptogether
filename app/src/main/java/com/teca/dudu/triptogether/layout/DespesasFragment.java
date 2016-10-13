package com.teca.dudu.triptogether.layout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.teca.dudu.triptogether.activity.AddDespesaActivity;
import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.DespesasAdapter;
import com.teca.dudu.triptogether.dao.DespesaDao;
import com.teca.dudu.triptogether.dao.ItemDespesaDao;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.dao.UsuarioViagemDao;
import com.teca.dudu.triptogether.model.Despesa;
import com.teca.dudu.triptogether.model.ItemDespesa;

import java.util.ArrayList;


public class DespesasFragment extends Fragment {
    ArrayList<ItemDespesa> despesas;
    FloatingActionButton fabAddDespesa;
    ListView listDespesas;
    ItemDespesaDao itemDespesaDao;
    public static final String ARG_PAGE = "ARG_PAGE";

    public DespesasFragment(){}
    public  DespesasFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DespesasFragment fragment = new DespesasFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_despesas, container, false);

        SharedPreferences sharedPref = getActivity().getSharedPreferences(
                getString(R.string.ID_VIAGEM_file_key), Context.MODE_PRIVATE);

        int id_viagem = sharedPref.getInt(getString(R.string.ID_VIAGEM_file_key),-1);

        //lista os itens despesas da viagem do usuario logado
        if(id_viagem !=-1) {
            listDespesas = (ListView)rootView.findViewById(R.id.list_despesas);

            itemDespesaDao = new ItemDespesaDao(rootView.getContext());
            despesas = new ArrayList<ItemDespesa>();

            despesas = itemDespesaDao.listaItensDeUmaViagem(id_viagem);

            if (despesas.size() >= 1) {
                DespesasAdapter adapter = new DespesasAdapter(rootView.getContext(), despesas);
                listDespesas.setAdapter(adapter);
            }
            //botao pra adicionar despesa
            fabAddDespesa = (FloatingActionButton) rootView.findViewById(R.id.fabadd_despesa);
            if (fabAddDespesa != null) {
                fabAddDespesa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentadddespesas = new Intent(v.getContext(), AddDespesaActivity.class);
                        startActivity(intentadddespesas);
                    }
                });

            }
        }

        return rootView;

    }
    /*
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();
    }*/

    @Override
    public void onResume() {
        super.onResume();
    }
}


