package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Exclui_Produto extends AppCompatActivity {
    EditText Txt_excluirP;
    Button Btn_excluir4;
    BancoDados db = new BancoDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exclui_produto);

        Txt_excluirP = (EditText)findViewById(R.id.Txt_excluirP);
        Btn_excluir4 = (Button)findViewById(R.id.Btn_excluir4);

        Btn_excluir4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_excluirP.getText().toString();

                if(codigo.isEmpty()) {
                    Toast.makeText(Tela_Exclui_Produto.this, "Nenhum Id foi Informado", Toast.LENGTH_LONG).show();
                } else {
                    String res = db.ValidarIdProduto(Integer.parseInt(codigo));
                    if (res.equals("ERRO")) {
                        Toast.makeText(Tela_Exclui_Produto.this, "Nenhum Produto encontrado", Toast.LENGTH_LONG).show();
                    } else {
                        Produto produto = new Produto();
                        produto.setId(Integer.parseInt(codigo));
                        db.apagarProduto(produto);

                        Toast.makeText(Tela_Exclui_Produto.this, "Produto Excluido com sucesso ", Toast.LENGTH_LONG).show();

                        Limpa_campos();

                    }
                }
            }
        });
    }
    void Limpa_campos(){
        Txt_excluirP.setText("");

    }
}