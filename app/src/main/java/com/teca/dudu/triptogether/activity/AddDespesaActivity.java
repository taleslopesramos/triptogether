package com.teca.dudu.triptogether.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.adapter.QuemUsouAdapter;
import com.teca.dudu.triptogether.dao.ItemDespesaDao;
import com.teca.dudu.triptogether.dao.UsuarioDao;
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

    int[] ids_usuarios;
    boolean[] checkUsuarios;
    boolean visible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_despesa);
        usuarioDao = new UsuarioDao(this);
        usuarios = new ArrayList<Usuario>();
        usuarios  = usuarioDao.listaUsuariosDeUmaViagem(1);
        ids_usuarios = new int[usuarios.size()];
        for(int i=0;i<usuarios.size();i++){
            ids_usuarios[i] = usuarios.get(i).get_id();
        }

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
                novaDespesa = new ItemDespesaDao(v.getContext());
                Float valor = new Float(valorTV.getText().toString());
                if(descTV.getText() != null && valorTV != null) {
                    ItemDespesa despesa = new ItemDespesa(null, "?", descTV.getText().toString(), categoriasSpinner.getSelectedItem().toString()
                            , null, valor, 1);
                    novaDespesa.salvarItemDespesa(despesa);
                } else{
                    Toast.makeText(v.getContext(), "Preencha os campos", Toast.LENGTH_SHORT).show();
                }

                Intent intentmain = new Intent(AddDespesaActivity.this, MainActivity.class);
                startActivity(intentmain);
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
                            if(checkUsuarios[i])
                                visible=true;

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
