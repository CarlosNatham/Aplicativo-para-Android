package com.example.a6444911.trab_m3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Tela_Cadastro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);
    }

    public void abrirActivityProduto(View view){
        startActivity(new Intent(getBaseContext(), Tela_Produto.class));
    }

    public void abrirActivityCliente(View view){
        startActivity(new Intent(getBaseContext(), Tela_Cliente.class));
    }

    public void abrirActivityFornecedor(View view){
        startActivity(new Intent(getBaseContext(), Tela_Fornecedor.class));
    }
}
