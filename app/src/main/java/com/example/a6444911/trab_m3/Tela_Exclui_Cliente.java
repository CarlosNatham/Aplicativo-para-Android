package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Tela_Exclui_Cliente extends AppCompatActivity {
    EditText Txt_excluir;
    Button Btn_excluir;
    BancoDados db = new BancoDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exclui_cliente);

        Txt_excluir = (EditText)findViewById(R.id.Txt_excluir);
        Btn_excluir = (Button)findViewById(R.id.Btn_excluir);

        Btn_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_excluir.getText().toString();

                if(codigo.isEmpty()) {
                    Toast.makeText(Tela_Exclui_Cliente.this, "Nenhum Id foi Informado", Toast.LENGTH_LONG).show();

                } else{
                   String res = db.ValidarIdCliente(Integer.parseInt(codigo));
                    if(res.equals("ERRO")){
                        Toast.makeText(Tela_Exclui_Cliente.this, "Nenhum Cliente encontrado", Toast.LENGTH_LONG).show();
                    }else {
                        Cliente cliente = new Cliente();
                        cliente.setId(Integer.parseInt(codigo));
                        db.apagarCliente(cliente);

                        Toast.makeText(Tela_Exclui_Cliente.this, "Cliente Excluido com sucesso", Toast.LENGTH_LONG).show();

                        Limpa_campos();
                    }
                }
            }
        });
    }
    void Limpa_campos(){
        Txt_excluir.setText("");

    }

}