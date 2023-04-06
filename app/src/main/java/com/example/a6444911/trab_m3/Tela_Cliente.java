package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Tela_Cliente extends AppCompatActivity {

    EditText Txt_id, Txt_nome, Txt_cpf, Txt_telefone, Txt_cep, Txt_pesquisaCliente, Txt_cidade;
    Button Btn_c, Btn_pesquisarCliente, Btn_pesquisaCEP;

    BancoDados db = new BancoDados(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cliente);

        Txt_id = (EditText)findViewById(R.id.Txt_id);
        Txt_nome = (EditText)findViewById(R.id.Txt_nome);
        Txt_cpf = (EditText)findViewById(R.id.Txt_cpf);
        Txt_telefone = (EditText)findViewById(R.id.Txt_telefone);
        Txt_cep = (EditText)findViewById(R.id.Txt_cep);
        Txt_pesquisaCliente = (EditText)findViewById(R.id.Txt_pesquisaCliente);
        Txt_cidade = (EditText)findViewById(R.id.Txt_cidade);

        Btn_c = (Button)findViewById(R.id.Btn_C);
        Btn_pesquisaCEP = (Button)findViewById(R.id.Btn_pesquisaCEP);
        Btn_pesquisarCliente = (Button)findViewById(R.id.Btn_pesquisarCliente);

        Btn_pesquisaCEP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = "http://viacep.com.br/ws/"+Txt_cep.getText()+"/json/";

                RequestQueue requestQueue = Volley.newRequestQueue(Tela_Cliente.this);

                JsonObjectRequest objectRequest = new JsonObjectRequest(
                        Request.Method.GET,
                        URL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                String cidade = response.toString();
                                String uf = response.toString();
                                String vet[] = cidade.split(",");
                                String vet2[] = uf.split(",");
                                String parteFinal = vet[4].substring(vet[4].lastIndexOf(":")+1);
                                String parteFinal2 = vet2[5].substring(vet2[5].lastIndexOf(":")+1);
                                Txt_cidade.setText(parteFinal + " - " + parteFinal2);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Tela_Cliente.this, "Cidade não encontrada", Toast.LENGTH_LONG).show();
                            }
                        }
                );
                requestQueue.add(objectRequest);
            }
        });

        Btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_id.getText().toString();
                String nome = Txt_nome.getText().toString();
                String cpf = Txt_cpf.getText().toString();
                String telefone = Txt_telefone.getText().toString();
                String cep = Txt_cep.getText().toString();
                String cid_uf = Txt_cidade.getText().toString();

                if(nome.isEmpty()){
                    Txt_nome.setError("Campo Obrigatorio");
                }else if(cpf.isEmpty()){
                    Txt_cpf.setError("Campo Obrigatorio");
                }else if(telefone.isEmpty()){
                    Txt_telefone.setError("Campo Obrigatorio");
                }else if(cep.isEmpty()){
                    Txt_cep.setError("Campo Obrigatorio");
                }else if(cid_uf.isEmpty()){
                    Txt_cidade.setError("Campo Obrigatorio");
                }else if(codigo.isEmpty()){
                    db.addCliente(new Cliente(nome, cpf, Long.parseLong(telefone), Long.parseLong(cep), cid_uf));
                    Toast.makeText(Tela_Cliente.this, "Adicionado com sucesso", Toast.LENGTH_LONG).show();
                    Limpa_campos();
                }else{
                    db.atualizaCliente(new Cliente(Integer.parseInt(codigo), nome, cpf, Long.parseLong(telefone), Long.parseLong(cep), cid_uf));
                    Toast.makeText(Tela_Cliente.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    Limpa_campos();
                }
            }
        });

        Btn_pesquisarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo2 = Txt_pesquisaCliente.getText().toString();
                if(codigo2.isEmpty()){
                    Toast.makeText(Tela_Cliente.this,"Nenhum Número Digitado",Toast.LENGTH_LONG).show();
                }else{
                    String res = db.ValidarIdCliente(Integer.parseInt(codigo2));
                    if(res.equals("ERRO")){
                        Toast.makeText(Tela_Cliente.this,"Nenhum Cliente Encontrado",Toast.LENGTH_LONG).show();
                    }else {
                        Cliente cliente = db.selecionarCliente(Integer.parseInt(codigo2));
                        Txt_id.setText(String.valueOf(cliente.getId()));
                        Txt_nome.setText(cliente.getNome());
                        Txt_cpf.setText(cliente.getCpf());
                        Txt_telefone.setText(String.valueOf(cliente.getTelefone()));
                        Txt_cep.setText(String.valueOf(cliente.getCep()));
                        Txt_cidade.setText(cliente.getCidade_uf());

                        Toast.makeText(Tela_Cliente.this, "Encontrado com sucesso", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    void Limpa_campos(){
        Txt_nome.setText("");
        Txt_cpf.setText("");
        Txt_telefone.setText("");
        Txt_cep.setText("");
        Txt_cidade.setText("");

        Txt_nome.requestFocus();
    }

}
