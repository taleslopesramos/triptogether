package com.teca.dudu.triptogether.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.QuemUsouAdapter;
import com.teca.dudu.triptogether.dao.DespesaDao;
import com.teca.dudu.triptogether.dao.ItemDespesaDao;
import com.teca.dudu.triptogether.dao.UsuarioDao;
import com.teca.dudu.triptogether.model.Despesa;
import com.teca.dudu.triptogether.model.ItemDespesa;
import com.teca.dudu.triptogether.model.Usuario;

import java.util.ArrayList;

public class AddDespesaActivity extends AppCompatActivity {
    ItemDespesaDao novaDespesa;

    TextView descTV, valorTV;
    Button addDespesaBtn, quemUsouBtn, quemPagouBtn;
    Spinner categoriasSpinner;
    ListView listViewQuemUsou;

    ArrayList<Usuario> usuarios;
    UsuarioDao usuarioDao;
    ArrayList<UsuarioPagante> usuariosPagantes;
    int id_usuario,id_viagem;

    int[] ids_usuarios;
    boolean[] checkUsuarios;
    boolean visible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_despesa);

        usuariosPagantes = new ArrayList<UsuarioPagante>();

        usuarioDao = new UsuarioDao(this);
        //pega id do usuario logado

        SharedPreferences sharedPref = this.getSharedPreferences(
                getString(R.string.ID_file_key), Context.MODE_PRIVATE);
        int id_usuario = sharedPref.getInt(getString(R.string.ID_file_key),-1);
        int id_viagem = -1;

        if(id_usuario != -1){//pega a idviagem atual do usuario logado
            id_viagem = usuarioDao.buscarIdViagem(id_usuario);
        }

        usuarios  = usuarioDao.listaUsuariosDeUmaViagem(id_viagem);


        descTV = (TextView)findViewById(R.id.desc_text);
        valorTV = (TextView)findViewById(R.id.valor_text);
        categoriasSpinner = (Spinner) findViewById(R.id.categorias_spinner);
        addDespesaBtn = (Button) findViewById(R.id.add_despesa_btn);
        quemUsouBtn = (Button) findViewById(R.id.quemusou_btn);

        listViewQuemUsou = (ListView) findViewById(R.id.listview_quemusou);
        // if(!visible)//nao mostra list view
        //listViewQuemUsou.setVisibility(View.INVISIBLE);


        ArrayAdapter<String> adapterCategorias = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapterCategorias.add("alimentacao");
        adapterCategorias.add("transporte");
        adapterCategorias.add("entretenimento");
        adapterCategorias.add("Goró");
        categoriasSpinner.setAdapter(adapterCategorias);

        addDespesaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cria o item da despesa em si com o valor e seus atributos e lança no bd pelo ItemDespesaDao
                novaDespesa = new ItemDespesaDao(v.getContext());
                Float valor = new Float(valorTV.getText().toString());

                if(descTV.getText() != null && valorTV != null) {//checa se os campos nao estao em branco
                    //add item despesa a tabela
                    ItemDespesa despesa = new ItemDespesa(null, "?", descTV.getText().toString(), categoriasSpinner.getSelectedItem().toString()
                            , null, valor, 1);

                    int id_itemdespesa = (int) novaDespesa.salvarItemDespesa(despesa);
                    //cria a relacao de usuario com despesa na tabela DespesaDao
                    setValorPagoQuemUsou();
                    boolean camposValidosValorPago = validaCamposValorPagoQuemUsou();
                    if(camposValidosValorPago){
                        criaDespesaDao(id_itemdespesa, v);
                    }


                } else{
                    Toast.makeText(v.getContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
                }
                finish();
                //Intent intentmain = new Intent(AddDespesaActivity.this, MainActivity.class);
                //startActivity(intentmain);
            }


            //valores dos textos dos editText de cada item do listview
            public void setValorPagoQuemUsou(){

                View itemV;
                EditText et;
                for (int i = 0; i <listViewQuemUsou.getCount(); i++) {
                    itemV = listViewQuemUsou.getChildAt(i);
                    et = (EditText) itemV.findViewById(R.id.itemquemusou_edittxt);
                    usuariosPagantes.get(i).setValorPago(et.getText().toString());
                }
            }
            //testa se a soma dos valores de entrada nos itens quemusou são iguais ao valor total da despesa
            public boolean validaCamposValorPagoQuemUsou(){
                float acm = 0;
                for(int i=0;i<usuariosPagantes.size();i++){
                    Float valor = new Float(usuariosPagantes.get(i).getValorPago());
                    acm += valor;

                }
                Float valorTotal = new Float(valorTV.getText().toString());
                if(acm == valorTotal)
                    return  true;
                else
                    return false;
            }
            //abre o DAO de despesa para inserir os valores pagos e devidos
            //de acordo com cada usuarioPagante
            public void criaDespesaDao(int id_itemdespesa, View v){
                Float valorTotal = new Float(valorTV.getText().toString());
                for(int i=0;i<usuariosPagantes.size();i++){
                    Float valorUsuario = new Float(usuariosPagantes.get(i).getValorPago());
                    Despesa despesa = new Despesa(valorTotal/usuariosPagantes.size(),
                            valorUsuario, id_itemdespesa,
                            usuariosPagantes.get(i).getId_usuario(),
                            1);
                    DespesaDao despesaDao = new DespesaDao(v.getContext());
                    despesaDao.salvarDespesa(despesa);
                }
            }
        });

        quemUsouBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog("quem usou", v);
            }
        });
    }



    private void createDialog(String quem, View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        CharSequence[] itens = new CharSequence[usuarios.size()];
        checkUsuarios = new boolean[usuarios.size()];
        for(int i =0;i<usuarios.size();i++){
            itens[i] =(CharSequence) usuarios.get(i).getNome().toString();
        }

        builder.setTitle(quem)
                .setMultiChoiceItems(itens, checkUsuarios, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        checkUsuarios[which] = isChecked;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int i=0; i<usuarios.size();i++){
                            if(checkUsuarios[i]) {
                                usuariosPagantes.add( new UsuarioPagante(ids_usuarios[i]));
                                visible = true;
                            }
                        }
                        if(visible)
                            displayQuemUsou();
                    }
                })
                .show();

    }
    public void displayQuemUsou(){
        ArrayList<Usuario> quemUsou = new ArrayList<Usuario>();
        for(int i=0; i<usuarios.size();i++){
            if(checkUsuarios[i]){
                quemUsou.add(usuarioDao.buscarUsuarioPorId(ids_usuarios[i]));

            }
        }
        QuemUsouAdapter adapter = new QuemUsouAdapter(this, quemUsou);
        listViewQuemUsou.setAdapter(adapter);
        //listViewQuemUsou.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
class UsuarioPagante{
    private String valorPago;
    private int id_usuario;

    public UsuarioPagante(int id_usuario, String valorPago) {
        this.id_usuario = id_usuario;
        this.valorPago = valorPago;
    }

    public UsuarioPagante(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public String getValorPago() {
        return valorPago;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setValorPago(String valorPago) {
        this.valorPago = valorPago;
    }
}