package com.example.pechincha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pechincha.model.Usuario;
import com.example.pechincha.repository.UsuarioDAO;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private UsuarioDAO usuarioDAO;
    private Button btnLogin, btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_login_email);
        etSenha = findViewById(R.id.et_login_senha);
        btnLogin = findViewById(R.id.btn_login);
        btnCadastrar = findViewById(R.id.btn_cadastrar);

        usuarioDAO = new UsuarioDAO(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastrarUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleLogin() {
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();

        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Usuario usuario = usuarioDAO.getUsuario(email, senha);
        if (usuario != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Email ou senha incorretos", Toast.LENGTH_SHORT).show();
        }
    }
}