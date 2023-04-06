package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tela_Exibe_Vendas extends AppCompatActivity {

    ListView listViewVendas;

    BancoDados db = new BancoDados(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exibe_venda);

        listViewVendas = (ListView)findViewById(R.id.listViewVendas);

        listarVendas();
    }

    public void listarVendas(){
        List<Vendas> vendas = db.Exibe_Vendas();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Tela_Exibe_Vendas.this, android.R.layout.simple_list_item_1, arrayList);
        listViewVendas.setAdapter(adapter);
        for(Vendas v : vendas){
            arrayList.add(v.getId() + " - " + v.getNome_cliente()+ " - " + v.getNome_funcionario() + " - " + v.getValor() +
                    " - " + v.getQtd_produtos() + " - " + v.getForma_pag());
            adapter.notifyDataSetChanged();
        }
    }
}