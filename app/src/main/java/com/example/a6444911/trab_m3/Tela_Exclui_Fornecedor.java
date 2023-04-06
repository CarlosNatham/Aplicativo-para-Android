package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Tela_Exclui_Fornecedor extends AppCompatActivity {
    EditText Txt_excluirF;
    Button Btn_excluir2;
    BancoDados db = new BancoDados(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exclui_fornecedor);

        Txt_excluirF = (EditText)findViewById(R.id.Txt_excluirF);
        Btn_excluir2 = (Button)findViewById(R.id.Btn_excluir2);

        Btn_excluir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_excluirF.getText().toString();

                if(codigo.isEmpty()) {
                    Toast.makeText(Tela_Exclui_Fornecedor.this, "Nenhum Id foi Informado", Toast.LENGTH_LONG).show();
                } else{
                    String res = db.ValidarIdFornecedor(Integer.parseInt(codigo));
                    if(res.equals("ERRO")){
                        Toast.makeText(Tela_Exclui_Fornecedor.this, "Nenhum Fornecedor encontrado", Toast.LENGTH_LONG).show();
                    }else {
                        Fornecedor fornecedor = new Fornecedor();
                        fornecedor.setId(Integer.parseInt(codigo));
                        db.apagarFornecedor(fornecedor);

                        Toast.makeText(Tela_Exclui_Fornecedor.this, "Fornecedor Excluido com sucesso", Toast.LENGTH_LONG).show();

                        Limpa_campos();
                    }
                }
            }
        });
    }
    void Limpa_campos(){
        Txt_excluirF.setText("");

    }
}
