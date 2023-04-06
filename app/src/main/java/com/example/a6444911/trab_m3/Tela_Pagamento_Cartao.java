package com.example.a6444911.trab_m3;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_Pagamento_Cartao extends AppCompatActivity {

    TextView Txt_titularCartao, Txt_numCartao, Txt_bandCartao,Txt_cod,Txt_parcela;
    EditText setaTitularCartao, setanumCartao, setabandCartao,setaCod,setaParcela;
    Button Btn_pagamento;
    Spinner  Spinner_parcela;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_pagamento_cartao);

        Txt_titularCartao = (TextView)findViewById(R.id.Txt_tiitularCartao);
        Txt_numCartao = (TextView) findViewById(R.id.Txt_numCartao);
        Txt_bandCartao = (TextView)findViewById(R.id.Txt_bandCartao);
        Txt_cod = (TextView) findViewById(R.id.Txt_cod);
        Txt_parcela = (TextView)findViewById(R.id.Txt_parcela);
        setaTitularCartao = (EditText)findViewById(R.id.setatitularCartao);
        setanumCartao = (EditText)findViewById(R.id.setanumCartao);
        setabandCartao = (EditText)findViewById(R.id.setabandCartao);
        setaCod = (EditText)findViewById(R.id.setaCod);
        setaParcela = (EditText)findViewById(R.id.Txt_setaParcela);

        Btn_pagamento = (Button)findViewById(R.id.Btn_pagamento);
        Spinner_parcela = (Spinner)findViewById(R.id.Spinner_parcela);

        setaParcela.setText(Tela_Venda.valor);

        Btn_pagamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titular = setaTitularCartao.getText().toString();
                String num = setanumCartao.getText().toString();
                String bandeira = setabandCartao.getText().toString();
                String cod = setaCod.getText().toString();

                if(titular.isEmpty()){
                    setaTitularCartao.setError("Campo Obrigatorio");
                }else if(num.isEmpty()){
                    setanumCartao.setError("Campo Obrigatorio");
                }else if(bandeira.isEmpty()){
                    setabandCartao.setError("Campo Obrigatorio");
                }else if(cod.isEmpty()){
                    setaCod.setError("Campo Obrigatorio");
                }else{
                    String parcela = Spinner_parcela.getSelectedItem().toString();
                    float res = Float.parseFloat(Tela_Venda.valor) / Float.parseFloat(parcela);
                    setaParcela.setText(String.valueOf(res));
                    Toast.makeText(Tela_Pagamento_Cartao.this, "Pagamento efetuado com Sucesso", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

