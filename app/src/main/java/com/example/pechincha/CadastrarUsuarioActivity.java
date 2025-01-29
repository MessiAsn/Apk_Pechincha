package com.example.pechincha;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.pechincha.model.Usuario;
import com.example.pechincha.repository.UsuarioDAO;

import java.io.IOException;

public class CadastrarUsuarioActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 101;
    private static final int MEDIA_IMAGES_PERMISSION_CODE = 102;

    private EditText etNome, etEmail, etSenha;
    private ImageView ivUsuarioFoto;
    private Uri imageUri;
    private UsuarioDAO usuarioDAO;
    private Button btnCadastrar;
    private Button btnSelecionarFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        etNome = findViewById(R.id.et_usuario_nome);
        etEmail = findViewById(R.id.et_usuario_email);
        etSenha = findViewById(R.id.et_usuario_senha);
        ivUsuarioFoto = findViewById(R.id.iv_usuario_foto);
        btnCadastrar = findViewById(R.id.btn_cadastrar_usuario);
        btnSelecionarFoto = findViewById(R.id.btn_selecionar_foto);

        usuarioDAO = new UsuarioDAO(this);

        btnSelecionarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(CadastrarUsuarioActivity.this,
                            Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CadastrarUsuarioActivity.this,
                                new String[]{Manifest.permission.READ_MEDIA_IMAGES}, MEDIA_IMAGES_PERMISSION_CODE);
                    } else {
                        openFileChooser();
                    }
                } else {
                    if (ContextCompat.checkSelfPermission(CadastrarUsuarioActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CadastrarUsuarioActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                    } else {
                        openFileChooser();
                    }
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();
                Usuario usuario = new Usuario(null, nome, email, senha, imageUri != null ? imageUri.toString() : null);
                usuarioDAO.insert(usuario);
                Toast.makeText(CadastrarUsuarioActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione uma foto"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ivUsuarioFoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE || requestCode == MEDIA_IMAGES_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFileChooser();
            } else {
                Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
            }
        }
    }
}