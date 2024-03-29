package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetofinal.Modelo.Pessoa;
import com.example.projetofinal.dao.PessoaDao;

public class FormPessoa extends AppCompatActivity {
    EditText editNome, editIdade, editEndereco, editTelefone;
    Button btnVariavel;
    Pessoa pessoa, altpessoa;
    PessoaDao pessoaDao;
    long retornoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pessoa);

        Intent i = getIntent();
        altpessoa = (Pessoa) i.getSerializableExtra("pessoa-enviada");
        pessoa = new Pessoa();
        pessoaDao = new PessoaDao(FormPessoa.this);

        editNome = (EditText) findViewById(R.id.editNome);
        editIdade = (EditText) findViewById(R.id.editIdade);
        editEndereco = (EditText) findViewById(R.id.editEndereco);
        editTelefone = (EditText) findViewById(R.id.editTelefone);
        btnVariavel = (Button) findViewById(R.id.btnVariavel);

        if(altpessoa != null) {
            btnVariavel.setText("Alterar");
        }else{
            btnVariavel.setText("Salvar");

        }

        btnVariavel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pessoa.setNome(editNome.getText().toString());
                pessoa.setId(Integer.parseInt(editIdade.getText().toString()));
                pessoa.setEndereco(editEndereco.getText().toString());
                pessoa.setTelefone(editTelefone.getText().toString());

                if (btnVariavel.getText().toString().equals("Salvar")){
                    retornoDB = pessoaDao.salvarPessoa(pessoa);
                    if (retornoDB == -1) {
                        alert("Erro ao Cadastrar");
                    }else{
                        alert("Cadastro realizado com sucesso");
                    }
                }else{

                }

                finish();
            }
        });

    }

    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}
