package com.teca.dudu.triptogether.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.UsuariosAdapter;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.model.Usuario;
import com.teca.dudu.triptogether.teste.UsuarioTeste;
import com.teca.dudu.triptogether.teste.ViagemTeste;

import java.util.ArrayList;

public class UsuariosFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";
    UsuarioDao usuarioDao;
    ArrayList<Usuario> usuarios;
    private int mPage;
    public UsuariosFragment(){}
    public  UsuariosFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        UsuariosFragment fragment = new UsuariosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments().getInt(ARG_PAGE);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_usuarios, container, false);

        //ADAPTER SET TO LISTVIEW
        usuarioDao = new UsuarioDao(rootView.getContext());
        usuarios = new ArrayList<Usuario>();
        usuarios = usuarioDao.listaUsuariosDeUmaViagem(1);
        ListView listUsuarios = (ListView) rootView.findViewById(R.id.list_usuarios);
        if(true) {
            UsuariosAdapter adapter = new UsuariosAdapter(rootView.getContext(), usuarios);
            listUsuarios.setAdapter(adapter);
        }
        return rootView;

    }
    /*
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(),
                Toast.LENGTH_LONG).show();
    }
    */
}
