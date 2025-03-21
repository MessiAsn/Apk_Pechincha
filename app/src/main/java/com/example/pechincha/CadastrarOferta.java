package com.example.pechincha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pechincha.model.Oferta;
import com.example.pechincha.repository.OfertaDAO;

public class CadastrarOferta extends AppCompatActivity {

    private EditText etOfertaTitulo;
    private EditText etOfertaPreco;
    private EditText etOfertaCupom;
    private ImageView ivOfertaImagem;
    private static final int PICK_IMAGE_REQUEST = 1;

    private long id;
    private OfertaDAO ofertaDAO;
    private Button ibSaveOferta;
    private Button ibDeleteOferta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastrar_oferta);

        ofertaDAO = new OfertaDAO(this);
        etOfertaTitulo = findViewById(R.id.et_ofertatitulo);
        etOfertaPreco = findViewById(R.id.et_ofertapreco);
        etOfertaCupom = findViewById(R.id.et_ofertacupom);
        ibSaveOferta = findViewById(R.id.ib_publicaroferta);
        ibDeleteOferta = findViewById(R.id.ibDeleteOferta);
        ivOfertaImagem = findViewById(R.id.ivOfertaImagem);

        ibDeleteOferta.setVisibility(View.INVISIBLE);

        ibSaveOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ofertaDAO.insert(getOferta());
                Toast.makeText(getApplicationContext(), "Oferta inserida", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        findViewById(R.id.btnSelecionarImagem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            ibDeleteOferta.setVisibility(View.VISIBLE);
            id = intent.getLongExtra("id", 0);
            setOferta(ofertaDAO.getById(id));
            ibSaveOferta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ofertaDAO.update(id, getOferta());
                    Toast.makeText(getApplicationContext(), "Oferta alterada", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    private Oferta getOferta() {
        Oferta oferta = new Oferta();
        oferta.setTitulo(etOfertaTitulo.getText().toString());
        oferta.setPreco(Double.parseDouble(etOfertaPreco.getText().toString()));
        oferta.setCupom(etOfertaCupom.getText().toString());
        return oferta;
    }

    private void setOferta(Oferta oferta) {
        etOfertaTitulo.setText(oferta.getTitulo());
        etOfertaPreco.setText(String.valueOf(oferta.getPreco()));
        etOfertaCupom.setText(oferta.getCupom());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            ivOfertaImagem.setImageURI(imageUri);
        }
    }

    public void ibDeleteOfertaOnClick(View v) {
        new AlertDialog.Builder(this).setTitle("Confirmação").setMessage("Tem certeza que deseja deletar esta oferta?")
                .setPositiveButton("Sim", (dialog, which) -> {
                    ofertaDAO.delete(id);
                    finish();
                    Toast.makeText(this, "Ação confirmada!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Não", (dialog, which) -> {
                }).show();
    }

    public void ibClearOfertaOnClick(View v) {
        setOferta(new Oferta());
    }
}