package com.example.a6444911.trab_m3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Tela_Consulta extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_consulta);
    }

    public void abrirActivityExibeCliente(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exibe_Cliente.class));
    }

    public void abrirActivityExibeProduto(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exibe_Produto.class));
    }

    public void abrirActivityExibeFornecedor(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exibe_Fornecedor.class));
    }

    public void abrirActivityExibeFuncionarios(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exibe_Funcionario.class));
    }

    public void abrirActivityExibeVendas(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exibe_Vendas.class));
    }
}
