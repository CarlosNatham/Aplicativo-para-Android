package com.example.a6444911.trab_m3;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tela_Exibe_Produto extends AppCompatActivity {

    ListView listViewProdutos;

    BancoDados db = new BancoDados(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exibe_produto);

        listViewProdutos = (ListView)findViewById(R.id.listViewProdutos);

        listarProdutos();
    }

    public void listarProdutos(){
        List<Produto> produtos = db.Exibe_Produto();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Tela_Exibe_Produto.this, android.R.layout.simple_list_item_1, arrayList);
        listViewProdutos.setAdapter(adapter);
        for(Produto p : produtos){
            arrayList.add(p.getId() + " - " + p.getNome()+ " - " + p.getValor() + " - " + p.getTamanho() + " - " + p.getEstoque());
            adapter.notifyDataSetChanged();
        }
    }
}
