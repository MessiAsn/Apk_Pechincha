package com.example.pechincha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pechincha.model.Usuario;
import com.example.pechincha.repository.UsuarioDAO;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private EditText etNome, etEmail, etSenha;
    private UsuarioDAO usuarioDAO;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        etNome = findViewById(R.id.et_usuario_nome);
        etEmail = findViewById(R.id.et_usuario_email);
        etSenha = findViewById(R.id.et_usuario_senha);
        btnCadastrar = findViewById(R.id.btn_cadastrar_usuario);

        usuarioDAO = new UsuarioDAO(this);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                Usuario usuario = new Usuario(null, nome, email, senha);
                usuarioDAO.insert(usuario);
                Toast.makeText(CadastrarUsuarioActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}