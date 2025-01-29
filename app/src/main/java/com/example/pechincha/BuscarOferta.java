package com.example.pechincha;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pechincha.recycler.OfertaAdapter;
import com.example.pechincha.repository.OfertaDAO;

public class BuscarOferta extends AppCompatActivity {

    private RecyclerView recyclerBusca;
    private OfertaAdapter ofertaAdapter;
    private OfertaDAO ofertaDAO;
    private EditText buscaOferta;
    private ImageButton svBusca;
    private Button btBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar_oferta);

        btBuscar = findViewById(R.id.btPesquisarFarmacia);
        buscaOferta = findViewById(R.id.et_BuscaOferta);
        ofertaDAO = new OfertaDAO(this);
        recyclerBusca = findViewById(R.id.recyclerBuscaOferta);
        recyclerBusca.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();

        if(intent.hasExtra("oferta")){
            String oferta = intent.getStringExtra("oferta");
            ofertaAdapter = new OfertaAdapter(this, ofertaDAO.buscaTitulo(oferta));
            recyclerBusca.setAdapter(ofertaAdapter);
        }
    }

    public void buscar(View v){
        ofertaAdapter = new OfertaAdapter(this, ofertaDAO.buscaTitulo(buscaOferta.getText().toString()));
        recyclerBusca.setAdapter(ofertaAdapter);
    }

    public void redirecionar(View v){
        buscaOferta.requestFocus();
    }

}
