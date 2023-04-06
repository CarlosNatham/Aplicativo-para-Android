package com.example.a6444911.trab_m3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Txt_login, Txt_senha;
    Button Btn_acessar, Btn_cadastrar;

    public static String username = null;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_login);

        Txt_login = (EditText)findViewById(R.id.Txt_login);
        Txt_senha = (EditText)findViewById(R.id.Txt_senha);

        Btn_acessar = (Button)findViewById(R.id.Btn_acessar);
        Btn_cadastrar = (Button)findViewById(R.id.Btn_cadastrar);

        Txt_login.requestFocus();

        Btn_acessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = Txt_login.getText().toString();
                String senha = Txt_senha.getText().toString();

                if(login.isEmpty()){
                    Txt_login.setError("Este campo é obrigatório");
                }else if(senha.isEmpty()){
                    Txt_senha.setError("Este campo é obrigatório");
                }else{
                    String res = db.ValidarLogin(login,senha);
                    if(res.equals("OK")){
                        Toast.makeText(MainActivity.this,"Login OK",Toast.LENGTH_LONG).show();
                        username = login;
                        abrirActivityInicial(view);
                    }else{
                        Toast.makeText(MainActivity.this,"Login Invalido",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void abrirActivityInicial(View view){
        startActivity(new Intent(getBaseContext(), Tela_Inicial.class));
    }

    public void abrirActivityCadastroF(View view){
        startActivity(new Intent(getBaseContext(), Tela_Cadastro_Funcionario.class));
    }
}