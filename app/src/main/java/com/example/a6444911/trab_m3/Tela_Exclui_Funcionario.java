package com.example.a6444911.trab_m3;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Exclui_Funcionario  extends AppCompatActivity {
    EditText Txt_excluirFu;
    Button Btn_excluir3;
    BancoDados db = new BancoDados(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_exclui_funcionario);

        Txt_excluirFu = (EditText)findViewById(R.id.Txt_excluirFu);
        Btn_excluir3 = (Button)findViewById(R.id.Btn_excluir3);

        Btn_excluir3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = Txt_excluirFu.getText().toString();

                if(codigo.isEmpty()) {
                    Toast.makeText(Tela_Exclui_Funcionario.this, "Nenhum Id foi Informado", Toast.LENGTH_LONG).show();
                }else if(codigo.equals("1")){
                    Toast.makeText(Tela_Exclui_Funcionario.this, "Esse usuario não pode ser apagado", Toast.LENGTH_LONG).show();
                    Limpa_campos();
                }else{
                    String res = db.ValidarIdFuncionario(Integer.parseInt(codigo));
                    if(res.equals("ERRO")){
                        Toast.makeText(Tela_Exclui_Funcionario.this, "Nenhum Funcionario encontrado", Toast.LENGTH_LONG).show();
                        Limpa_campos();
                    }else{
                        Funcionario funcionario = new Funcionario();
                        funcionario.setId(Integer.parseInt(codigo));
                        db.apagarFuncionario(funcionario);

                        Toast.makeText(Tela_Exclui_Funcionario.this, "Funcionário Excluido com sucesso", Toast.LENGTH_LONG).show();

                        Limpa_campos();
                    }
                }
            }
        });
    }
    void Limpa_campos(){
        Txt_excluirFu.setText("");

    }
}