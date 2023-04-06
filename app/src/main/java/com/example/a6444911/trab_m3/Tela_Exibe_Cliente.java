package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tela_Exibe_Cliente extends AppCompatActivity{

    ListView listViewClientes;

    BancoDados db = new BancoDados(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exibe_cliente);

        listViewClientes = (ListView)findViewById(R.id.listViewClientes);

        listarClientes();
    }

    public void listarClientes(){
        List<Cliente> clientes = db.Exibe_cliente();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Tela_Exibe_Cliente.this, android.R.layout.simple_list_item_1, arrayList);
        listViewClientes.setAdapter(adapter);
        for(Cliente c : clientes){
            arrayList.add(c.getId() + " - " + c.getCpf() + " - " + c.getNome() + " - " + c.getTelefone() + " - " + c.getCidade_uf());
            adapter.notifyDataSetChanged();
        }
    }
}
