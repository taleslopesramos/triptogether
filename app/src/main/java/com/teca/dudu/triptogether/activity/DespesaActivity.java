package com.teca.dudu.triptogether.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.UsuariosAdapter;
import com.teca.dudu.triptogether.dao.DespesaDao;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.model.Despesa;
import com.teca.dudu.triptogether.model.Usuario;

import java.util.ArrayList;

public class DespesaActivity extends AppCompatActivity {
    private DespesaDao despesaDao;
    private UsuarioDao usuarioDao;
    private ArrayList<Despesa> despesas;
    private ArrayList<Usuario> usuarios;

    private ListView lv_usuarios;
    private TextView tv_nome,tv_valor;
    private ImageView iv_categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
        Bundle extras = getIntent().getExtras();

        lv_usuarios = (ListView) findViewById(R.id.list_usuarios);
        tv_nome = (TextView) findViewById(R.id.ad_nome_itemdespesa);
        tv_valor = (TextView) findViewById(R.id.ad_valor_txt);
        iv_categoria = (ImageView) findViewById(R.id.imagem_despesa);

        int id_itemDespesa = extras.getInt("id_item");
        int id_viagem = extras.getInt("id_viagem");
        String nome = extras.getString("descricao");
        float valor = extras.getFloat("valor");
        int categoria = extras.getInt("categoria");

        tv_nome.setText(nome);
        tv_valor.setText(tv_valor.getText().toString()+Float.toString(valor));

        despesaDao = new DespesaDao(this);
        despesas = despesaDao.listaDespesasDeUmItem(id_itemDespesa,id_viagem);
        despesaDao.close();

        usuarioDao = new UsuarioDao(this);
        usuarios = usuarioDao.listaUsuariosDeUmItem(id_viagem,id_itemDespesa);
        usuarioDao.close();

        if(usuarios.size() >= 1){
            UsuariosAdapter adapter = new UsuariosAdapter(this, usuarios,id_itemDespesa);
            lv_usuarios.setAdapter(adapter);
        }
    }
}
