package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tela_Exibe_Funcionario extends AppCompatActivity {

    ListView listViewFuncionarios;

    BancoDados db = new BancoDados(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exibe_funcionario);

        listViewFuncionarios = (ListView)findViewById(R.id.listViewFuncionarios);

        listarFuncionarios();
    }

    public void listarFuncionarios(){
        List<Funcionario> funcionarios = db.Exibe_funcionarios();
        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(Tela_Exibe_Funcionario.this, android.R.layout.simple_list_item_1, arrayList);
        listViewFuncionarios.setAdapter(adapter);
        for(Funcionario fu : funcionarios){
            arrayList.add(fu.getId() + " - " + fu.getNome() + " - " + fu.getUsername() + " - " + fu.getSalario());
            adapter.notifyDataSetChanged();
        }
    }
}
