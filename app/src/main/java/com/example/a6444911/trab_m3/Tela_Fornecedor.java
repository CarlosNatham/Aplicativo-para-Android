package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Fornecedor extends AppCompatActivity {

    EditText Txt_id3, Txt_nomeEmp, Txt_cnpj, Txt_ie, Txt_pesquisaFornecedor;
    Button Btn_salvarF, Btn_pesquisaFornecedor;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_fornecedor);

        Txt_id3 = (EditText)findViewById(R.id.Txt_id3);
        Txt_nomeEmp = (EditText)findViewById(R.id.Txt_nomeEmp);
        Txt_cnpj = (EditText)findViewById(R.id.Txt_cnpj);
        Txt_ie = (EditText)findViewById(R.id.Txt_ie);
        Txt_pesquisaFornecedor = (EditText)findViewById(R.id.Txt_pesquisaFornecedor);

        Btn_salvarF = (Button)findViewById(R.id.Btn_salvarF);
        Btn_pesquisaFornecedor = (Button)findViewById(R.id.Btn_pesquisaFornecedor);

        Btn_salvarF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_id3.getText().toString();
                String nomeEmp = Txt_nomeEmp.getText().toString();
                String cnpj = Txt_cnpj.getText().toString();
                String ie = Txt_ie.getText().toString();

                if(nomeEmp.isEmpty()){
                    Txt_nomeEmp.setError("Campo Obrigatorio");
                }else if(cnpj.isEmpty()){
                    Txt_cnpj.setError("Campo Obrigatorio");
                }else if(ie.isEmpty()){
                    Txt_ie.setError("Campo Obrigatorio");
                }else if(codigo.isEmpty()){
                    db.addFornecedor(new Fornecedor(nomeEmp, cnpj, Integer.parseInt(ie)));
                    Toast.makeText(Tela_Fornecedor.this, "Adicionado com sucesso", Toast.LENGTH_LONG).show();
                    Limpa_campos();
                }else{
                    db.atualizaFornecedor(new Fornecedor(Integer.parseInt(codigo), nomeEmp, cnpj, Integer.parseInt(ie)));
                    Toast.makeText(Tela_Fornecedor.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    Limpa_campos();
                }
            }
        });

        Btn_pesquisaFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo2 = Txt_pesquisaFornecedor.getText().toString();
                if(codigo2.isEmpty()){
                    Toast.makeText(Tela_Fornecedor.this,"Nenhum Número Digitado",Toast.LENGTH_LONG).show();
                }else{
                    String res = db.ValidarIdFornecedor(Integer.parseInt(codigo2));
                    if(res.equals("ERRO")){
                        Toast.makeText(Tela_Fornecedor.this,"Nenhum Fornecedor Encontrado",Toast.LENGTH_LONG).show();
                    }else {
                        Fornecedor fornecedor = db.selecionarFornecedor(Integer.parseInt(codigo2));
                        Txt_id3.setText(String.valueOf(fornecedor.getId()));
                        Txt_nomeEmp.setText(fornecedor.getNome());
                        Txt_cnpj.setText(fornecedor.getCNPJ());
                        Txt_ie.setText(String.valueOf(fornecedor.getIe()));

                        Toast.makeText(Tela_Fornecedor.this, "Encontrado com sucesso", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    void Limpa_campos(){
        Txt_nomeEmp.setText("");
        Txt_cnpj.setText("");
        Txt_ie.setText("");

        Txt_nomeEmp.requestFocus();
    }

}