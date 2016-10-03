package com.teca.dudu.triptogether.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.teca.dudu.triptogether.R;
import com.teca.dudu.triptogether.dao.ItemDespesaDao;
import com.teca.dudu.triptogether.model.ItemDespesa;

import java.util.ArrayList;

public class AddDespesaActivity extends AppCompatActivity {
    ItemDespesaDao novaDespesa;
    TextView descTV, valorTV;
    Button addDespesaBtn;
    Spinner categoriasSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_despesa);



        descTV = (TextView)findViewById(R.id.desc_text);
        valorTV = (TextView)findViewById(R.id.valor_text);
        categoriasSpinner = (Spinner) findViewById(R.id.categorias_spinner);
        addDespesaBtn = (Button) findViewById(R.id.add_despesa_btn);


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

    }
}
