package com.example.a6444911.trab_m3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Cadastro_Funcionario extends AppCompatActivity {
    EditText Txt_id0, Txt_nome, Txt_usuario, Txt_password, Txt_salario, Txt_pesquisaFunc;
    Button Btn_pesquisar, Btn_cadastrar;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro_funcionario);

        Txt_id0 = (EditText) findViewById(R.id.Txt_id0);
        Txt_nome = (EditText) findViewById(R.id.Txt_nome);
        Txt_usuario = (EditText) findViewById(R.id.Txt_usuario);
        Txt_password = (EditText) findViewById(R.id.Txt_password);
        Txt_salario = (EditText) findViewById(R.id.Txt_salario);
        Txt_pesquisaFunc = (EditText) findViewById(R.id.Txt_pesquisaFunc);

        Btn_cadastrar = (Button) findViewById(R.id.Btn_cadastrar);
        Btn_pesquisar = (Button) findViewById(R.id.Btn_pesquisar);

        Btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_id0.getText().toString();
                String nome = Txt_nome.getText().toString();
                String usuario = Txt_usuario.getText().toString();
                String senha = Txt_password.getText().toString();
                String salario = Txt_salario.getText().toString();

                if(nome.isEmpty()) {
                    Txt_nome.setError("Campo obrigatório");
                }else if(usuario.isEmpty()) {
                    Txt_usuario.setError("Campo obrigatório");
                }else if(senha.isEmpty()){
                    Txt_password.setError("Campo obrigatório");
                }else if(salario.isEmpty()){
                    Txt_salario.setError("Campo obrigatório");
                }else if(codigo.isEmpty()){
                    String res = db.ValidarEmail(usuario);
                    if(res.equals("ERRO")){
                        Txt_usuario.setError("Este email já esta em uso");
                    }else{
                        db.addFuncionario(new Funcionario(usuario,senha,Float.parseFloat(salario),nome));
                        Toast.makeText(Tela_Cadastro_Funcionario.this,"Salvo com sucesso",Toast.LENGTH_LONG).show();
                        Limpa_campos();
                    }
                }else{
                    db.atualizaFuncionario(new Funcionario(Integer.parseInt(codigo), usuario, senha,Float.parseFloat(salario),nome));
                    Toast.makeText(Tela_Cadastro_Funcionario.this,"Atualizado com sucesso",Toast.LENGTH_LONG).show();
                    Limpa_campos();
                }
            }
        });

        Btn_pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo2 = Txt_pesquisaFunc.getText().toString();
                if(codigo2.isEmpty()){
                    Toast.makeText(Tela_Cadastro_Funcionario.this,"Nenhum Número Digitado",Toast.LENGTH_LONG).show();
                }else{
                    String res = db.ValidarIdFuncionario(Integer.parseInt(codigo2));
                    if(res.equals("ERRO")){
                        Toast.makeText(Tela_Cadastro_Funcionario.this,"Nenhum Funcionario Encontrado",Toast.LENGTH_LONG).show();
                    }else {
                        Funcionario funcionario = db.selecionarFuncionario(Integer.parseInt(codigo2));
                        Txt_id0.setText(String.valueOf(funcionario.getId()));
                        Txt_nome.setText(funcionario.getNome());
                        Txt_usuario.setText(funcionario.getUsername());
                        Txt_password.setText(funcionario.getPassword());
                        Txt_salario.setText(String.valueOf(funcionario.getSalario()));

                        Toast.makeText(Tela_Cadastro_Funcionario.this, "Encontrado com sucesso", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    void Limpa_campos(){
        Txt_id0.setText("");
        Txt_nome.setText("");
        Txt_usuario.setText("");
        Txt_password.setText("");
        Txt_salario.setText("");

        Txt_nome.requestFocus();
    }
}