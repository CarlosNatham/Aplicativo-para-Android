package com.example.a6444911.trab_m3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Tela_Venda extends AppCompatActivity {

    float valorT = 0;
    int qtdT = 0;
    String c = "Cartão", d = "Dinheiro";
    public static String valor = null;

    TextView Txt_selectCliente, Txt_selectFunc, Txt_selectProd, Txt_qtd,  Txt_total, Txt_formaPag;
    Button Btn_add, Btn_cartao, Btn_dinheiro;
    EditText seta_Total;
    Spinner spinnerCliente, spinnerFunc, spinnerProd, spinnerQtd;

    ArrayAdapter<String> adapter;

    BancoDados db = new BancoDados(this);

    Produto prod =  new Produto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_venda);

        Txt_selectCliente = (TextView) findViewById(R.id.Txt_selectCliente);
        Txt_selectFunc = (TextView) findViewById(R.id.Txt_selectFunc);
        Txt_selectProd = (TextView) findViewById(R.id.Txt_selectProd);
        Txt_qtd = (TextView) findViewById(R.id.Txt_qtd);
        Txt_total = (TextView) findViewById(R.id.Txt_total);
        Txt_formaPag = (TextView) findViewById(R.id.Txt_formaPag);

        Btn_add = (Button) findViewById(R.id.Btn_add);
        Btn_cartao = (Button) findViewById(R.id.Btn_cartao);
        Btn_dinheiro = (Button) findViewById(R.id.Btn_dinheiro);

        seta_Total = (EditText) findViewById(R.id.seta_Total);

        spinnerCliente = (Spinner) findViewById(R.id.spinnerCliente);
        spinnerFunc = (Spinner) findViewById(R.id.spinnerFunc);
        spinnerProd = (Spinner) findViewById(R.id.spinnerProd);
        spinnerQtd = (Spinner) findViewById(R.id.Spinner_parcela);

        ArrayList<String> clientes = db.getClientes();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, clientes);
        spinnerCliente.setAdapter(adapter);

        ArrayList<String> funcionarios = db.getFuncionario();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, funcionarios);
        spinnerFunc.setAdapter(adapter);

        ArrayList<String> produtos = db.getProduto();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, produtos);
        spinnerProd.setAdapter(adapter);

        Btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String produto = spinnerProd.getSelectedItem().toString();
                prod = db.selecionarProduto2(produto);
                String qtd = spinnerQtd.getSelectedItem().toString();
                if(Integer.parseInt(qtd) > prod.getEstoque()){
                    Toast.makeText(Tela_Venda.this, "Quantidade em estoque Insuficiente", Toast.LENGTH_LONG).show();
                }else{
                    valorT = valorT + (Integer.parseInt(qtd) * prod.getValor());
                    qtdT = qtdT + Integer.parseInt(qtd);
                    seta_Total.setText((String.valueOf(valorT)));
                    valor = String.valueOf(valorT);
                    int novoEst = prod.getEstoque() - Integer.parseInt(qtd);
                    prod.setEstoque(novoEst);
                    db.atualizaEstoqueProduto(prod);
                }
            }
        });

        Btn_dinheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seta_Total.getText().length() == 0){
                    Toast.makeText(Tela_Venda.this, "Selecione um produto", Toast.LENGTH_LONG).show();
                }else{
                    String cliente = spinnerCliente.getSelectedItem().toString();
                    String funcionario = spinnerFunc.getSelectedItem().toString();
                    db.addVendas(new Vendas(cliente, funcionario, valorT, qtdT, d));
                    //Toast.makeText(Tela_Venda.this, "Venda com sucesso", Toast.LENGTH_LONG).show();
                    abrirActivityDinheiro(view);
                }
            }
        });

        Btn_cartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (seta_Total.getText().length() == 0) {
                    Toast.makeText(Tela_Venda.this, "Selecione um produto", Toast.LENGTH_LONG).show();
                } else {
                    String cliente = spinnerCliente.getSelectedItem().toString();
                    String funcionario = spinnerFunc.getSelectedItem().toString();
                    db.addVendas(new Vendas(cliente, funcionario, valorT, qtdT, c));
                    //Toast.makeText(Tela_Venda.this, "Venda com sucesso", Toast.LENGTH_LONG).show();
                    abrirActivityCartao(view);
                }
            }
        });
    }

    public void abrirActivityDinheiro(View view){
        startActivity(new Intent((getBaseContext()), Tela_Pagamento_Dinheiro.class));
    }

    public void abrirActivityCartao(View view){
        startActivity(new Intent((getBaseContext()), Tela_Pagamento_Cartao.class));
    }
}
