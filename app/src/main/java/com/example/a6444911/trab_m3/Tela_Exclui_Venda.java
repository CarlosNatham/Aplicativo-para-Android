package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Exclui_Venda extends AppCompatActivity {
    EditText Txt_excluirV;
    Button Btn_excluir5;
    BancoDados db = new BancoDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exclui_venda);

        Txt_excluirV = (EditText)findViewById(R.id.Txt_excluirV);
        Btn_excluir5 = (Button)findViewById(R.id.Btn_excluir5);

        Btn_excluir5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_excluirV.getText().toString();

                if(codigo.isEmpty()) {
                    Toast.makeText(Tela_Exclui_Venda.this, "Nenhum Id foi Informado", Toast.LENGTH_LONG).show();
                } else {
                    String res = db.ValidarIdVendas(Integer.parseInt(codigo));
                    if (res.equals("ERRO")) {
                        Toast.makeText(Tela_Exclui_Venda.this, "Nenhuma Venda encontrada", Toast.LENGTH_LONG).show();
                    } else {
                        Vendas vendas = new Vendas();
                        vendas.setId(Integer.parseInt(codigo));
                        db.apagarVenda(vendas);

                        Toast.makeText(Tela_Exclui_Venda.this, "Venda Excluida com sucesso", Toast.LENGTH_LONG).show();

                        Limpa_campos();
                    }
                }
            }
        });

    }
    void Limpa_campos(){
        Txt_excluirV.setText("");

    }
}