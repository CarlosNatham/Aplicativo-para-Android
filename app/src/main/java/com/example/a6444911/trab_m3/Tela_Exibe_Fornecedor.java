package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tela_Exibe_Fornecedor extends AppCompatActivity{

    ListView listViewFornecedores;

    BancoDados db = new BancoDados(this);
    ArrayAdapter<String> adapter2;
    ArrayList<String> arrayList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exibe_fornecedor);

        listViewFornecedores = (ListView)findViewById(R.id.listViewFornecedores);

        listarFornecedores();
    }

    public void listarFornecedores(){
        List<Fornecedor> fornecedor = db.Exibe_fornecedor();
        arrayList2 = new ArrayList<String>();
        adapter2 = new ArrayAdapter<String>(Tela_Exibe_Fornecedor.this, android.R.layout.simple_list_item_1, arrayList2);
        listViewFornecedores.setAdapter(adapter2);
        for(Fornecedor f : fornecedor){
            arrayList2.add(f.getId() + " - " + f.getNome() + " - " + f.getCNPJ() + " - " + f.getIe());
            adapter2.notifyDataSetChanged();
        }
    }
}
