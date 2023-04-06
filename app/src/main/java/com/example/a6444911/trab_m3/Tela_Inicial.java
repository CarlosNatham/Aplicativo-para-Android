package com.example.a6444911.trab_m3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Tela_Inicial extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial);
    }

    public void abrirActivityCadastro(View view){
        startActivity(new Intent(getBaseContext(), Tela_Cadastro.class));
    }

    public void abrirActivityConsulta(View view){
        startActivity(new Intent((getBaseContext()), Tela_Consulta.class));
    }

    public void abrirActivityExcluir(View view){
        startActivity(new Intent((getBaseContext()), Tela_Excluir.class));
    }

    public void abrirActivityVender(View view){
        startActivity(new Intent((getBaseContext()), Tela_Venda.class));
    }
}
