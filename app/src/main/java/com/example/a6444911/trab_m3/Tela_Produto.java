package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Tela_Produto extends AppCompatActivity {

    EditText Txt_id2, Txt_nome2, Txt_valor, Txt_tamanho, Txt_estoque, Txt_pesquisaProduto;
    Button Btn_salvarP, Btn_pesquisarProduto;

    BancoDados db = new BancoDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_produto);

        Txt_id2 = (EditText)findViewById(R.id.Txt_id2);
        Txt_nome2 = (EditText)findViewById(R.id.Txt_nome2);
        Txt_valor = (EditText)findViewById(R.id.Txt_valor);
        Txt_tamanho = (EditText)findViewById(R.id.Txt_tamanho);
        Txt_estoque = (EditText)findViewById(R.id.Txt_estoque);
        Txt_pesquisaProduto = (EditText)findViewById(R.id.Txt_pesquisaProduto);

        Btn_salvarP = (Button)findViewById(R.id.Btn_salvarP);
        Btn_pesquisarProduto = (Button)findViewById(R.id.Btn_pesquisarProduto);

        Btn_salvarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_id2.getText().toString();
                String nome = Txt_nome2.getText().toString();
                String valor = Txt_valor.getText().toString();
                String tamanho = Txt_tamanho.getText().toString();
                String estoque = Txt_estoque.getText().toString();

                if(nome.isEmpty()){
                    Txt_nome2.setError("Campo Obrigatorio");
                }else if(valor.isEmpty()){
                    Txt_valor.setError("Campo Obrigatorio");
                }else if(tamanho.isEmpty()){
                    Txt_tamanho.setError("Campo Obrigatorio");
                }else if(estoque.isEmpty()){
                    Txt_estoque.setError("Campo Obrigatorio");
                }else if(codigo.isEmpty()){
                    db.addProduto(new Produto(nome, Float.parseFloat(valor), tamanho, Integer.parseInt(estoque)));
                    Toast.makeText(Tela_Produto.this, "Adicionado com sucesso", Toast.LENGTH_LONG).show();
                    Limpa_campos();
                }else{
                    db.atualizaProduto(new Produto(Integer.parseInt(codigo), nome, Float.parseFloat(valor), tamanho, Integer.parseInt(estoque)));
                    Toast.makeText(Tela_Produto.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    Limpa_campos();
                }
            }
        });

        Btn_pesquisarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String codigo2 = Txt_pesquisaProduto.getText().toString();
                if(codigo2.isEmpty()){
                    Toast.makeText(Tela_Produto.this,"Nenhum Número Digitado",Toast.LENGTH_LONG).show();
                }else{
                    String res = db.ValidarIdProduto(Integer.parseInt(codigo2));
                    if(res.equals("ERRO")){
                        Toast.makeText(Tela_Produto.this,"Nenhum Produto Encontrado",Toast.LENGTH_LONG).show();
                    }else {
                        Produto produto = db.selecionarProduto(Integer.parseInt(codigo2));
                        Txt_id2.setText(String.valueOf(produto.getId()));
                        Txt_nome2.setText(produto.getNome());
                        Txt_valor.setText(String.valueOf(produto.getValor()));
                        Txt_tamanho.setText(produto.getTamanho());
                        Txt_estoque.setText(String.valueOf(produto.getEstoque()));

                        Toast.makeText(Tela_Produto.this, "Encontrado com sucesso", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    void Limpa_campos(){
        Txt_nome2.setText("");
        Txt_valor.setText("");
        Txt_tamanho.setText("");
        Txt_estoque.setText("");

        Txt_nome2.requestFocus();
    }
}