package com.teca.dudu.triptogether.layout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.activity.AddDespesaActivity;
import com.teca.dudu.triptogether.activity.DespesaActivity;
import com.teca.dudu.triptogether.adapter.DespesasAdapter;
import com.teca.dudu.triptogether.dao.DespesaDao;
import com.teca.dudu.triptogether.dao.ItemDespesaDao;
import com.teca.dudu.triptogether.model.ItemDespesa;

import java.util.ArrayList;
import java.util.Collections;


public class DespesasFragment extends Fragment {
    ArrayList<ItemDespesa> despesas;
    FloatingActionButton fabAddDespesa;
    ListView listDespesas;
    DespesasAdapter adapter;
    ItemDespesaDao itemDespesaDao;
    DespesaDao despesaDao;
    int selectCount = 0;
    ArrayList<ItemDespesa> selectDespesas = new ArrayList<ItemDespesa>();
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

        final int id_viagem = sharedPref.getInt(getString(R.string.ID_VIAGEM_file_key),-1);

        //lista os itens despesas da viagem do usuario logado
        if(id_viagem !=-1) {
            listDespesas = (ListView)rootView.findViewById(R.id.list_despesas);

            itemDespesaDao = new ItemDespesaDao(rootView.getContext());
            despesaDao = new DespesaDao(rootView.getContext());
            despesas = new ArrayList<ItemDespesa>();

            despesas = itemDespesaDao.listaItensDeUmaViagem(id_viagem);

            listDespesas.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });

            if (despesas.size() >= 1) {
                Collections.reverse(despesas);//dar display nas despesas mais recenter primeiro
                adapter = new DespesasAdapter(rootView.getContext(), despesas);
                listDespesas.setAdapter(adapter);
                listDespesas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
                listDespesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        final ItemDespesa itemDespesaSelecionado = (ItemDespesa) adapterView.getItemAtPosition(i);

                        Intent intent = new Intent(getContext(),DespesaActivity.class);
                        intent.putExtra("id_item", itemDespesaSelecionado.get_id());
                        intent.putExtra("id_viagem",id_viagem);
                        intent.putExtra("categoria",itemDespesaSelecionado.getCategoria());
                        intent.putExtra("descricao",itemDespesaSelecionado.getDescricao());
                        intent.putExtra("valor",itemDespesaSelecionado.getValor());
                        startActivity(intent);
                    }
                });
                listDespesas.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                    @Override
                    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                        if (selectCount == 0)
                            mode.setTitle("da");
                        else if (selectCount > 1)
                            mode.setTitle(selectCount + "despesas selecionadas");
                        else
                            mode.setTitle(selectCount + "despesa selecionada");
                        if(checked) {
                            selectCount++;
                            if(!selectDespesas.contains(despesas.get(position)))
                                selectDespesas.add(despesas.get(position));
                            listDespesas.getChildAt(position).setBackgroundColor(Color.LTGRAY);
                        } else{
                            selectCount--;
                            listDespesas.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                        }
                    }

                    @Override
                    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                        MenuInflater menuInflater =  mode.getMenuInflater();
                        menuInflater.inflate(R.menu.context_menu, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                        switch(item.getItemId()){
                            case (R.id.delete_ic):
                                for(ItemDespesa despesa : selectDespesas ){
                                    adapter.remove(despesa);
                                    int id_itemdespesa = despesa.get_id();
                                    despesaDao.removerDespesasPorItemDespesa(id_itemdespesa);
                                    itemDespesaDao.removerItemDespesa(id_itemdespesa);


                                }
                                mode.finish();
                                return true;
                            default:
                                break;
                        }
                        return false;
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode mode) {
                        selectCount = 0;
                        selectDespesas.clear();
                        adapter.notifyDataSetChanged();
                    }
                });
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
        itemDespesaDao.close();
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



