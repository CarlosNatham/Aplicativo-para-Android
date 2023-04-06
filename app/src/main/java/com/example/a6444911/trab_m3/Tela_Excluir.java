package com.example.a6444911.trab_m3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Tela_Excluir extends AppCompatActivity {

    Button Btn_FuncionarioExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_excluir);

        Btn_FuncionarioExcluir = (Button)findViewById(R.id.Btn_FuncionarioExcluir);

        Btn_FuncionarioExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.username.equals("admin")){
                    abrirActivityExcluirFuncionario(v);
                }else{
                    Toast.makeText(Tela_Excluir.this,"Usuario sem permissão de acesso",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void abrirActivityExcluirCliente(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exclui_Cliente.class));
    }

    public void abrirActivityExcluirFornecedor(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exclui_Fornecedor.class));
    }

    public void abrirActivityExcluirFuncionario(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exclui_Funcionario.class));
    }

    public void abrirActivityExcluirProduto(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exclui_Produto.class));
    }

    public void abrirActivityExcluirVenda(View view){
        startActivity(new Intent(getBaseContext(), Tela_Exclui_Venda.class));
    }
}
