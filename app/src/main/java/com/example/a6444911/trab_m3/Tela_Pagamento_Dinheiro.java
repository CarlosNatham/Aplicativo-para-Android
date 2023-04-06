package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Pagamento_Dinheiro extends AppCompatActivity {

    TextView Txt_valorTotalP, Txt_dinheiro, Txt_troco;
    EditText Txt_editValor, Txt_editDinheiro, Txt_editTroco;
    Button Btn_pagar;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pagamento_dinheiro);

        Txt_valorTotalP = (TextView)findViewById(R.id.Txt_valorTotalP);
        Txt_editValor = (EditText)findViewById(R.id.Txt_editValor);
        Txt_dinheiro = (TextView)findViewById(R.id.Txt_dinheiro);
        Txt_editDinheiro = (EditText)findViewById(R.id.Txt_editDinheiro);
        Txt_troco = (TextView)findViewById(R.id.Txt_troco);
        Txt_editTroco = (EditText)findViewById(R.id.Txt_editTroco);

        Btn_pagar = (Button)findViewById(R.id.Btn_pagar);

        Txt_editValor.setText(Tela_Venda.valor);

        Btn_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dinheiro = Txt_editDinheiro.getText().toString();
                if(dinheiro.isEmpty()){
                    Txt_editDinheiro.setError("Campo Obrigatorio");
                }else if(Float.parseFloat(dinheiro) < Float.parseFloat(Tela_Venda.valor)){
                    Toast.makeText(Tela_Pagamento_Dinheiro.this, "Você é pobre Brow!", Toast.LENGTH_LONG).show();
                }else{
                    float res = Float.parseFloat(dinheiro) - Float.parseFloat(Tela_Venda.valor);
                    Txt_editTroco.setText(String.valueOf(res));
                    Toast.makeText(Tela_Pagamento_Dinheiro.this, "Pagamento efetuado com Sucesso", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
