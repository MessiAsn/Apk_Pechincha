package com.example.pechincha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pechincha.recycler.OfertaAdapter;
import com.example.pechincha.repository.OfertaDAO;

public class BuscarOferta extends AppCompatActivity {

    private RecyclerView recyclerBusca;
    private OfertaAdapter ofertaAdapter;
    private OfertaDAO ofertaDAO;
    private EditText buscaOferta;
    private EditText etPrecoMinimo;
    private EditText etPrecoMaximo;
    private Button btBuscar;
    private Button btAplicarFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar_oferta);

        btBuscar = findViewById(R.id.btPesquisarFarmacia);
        buscaOferta = findViewById(R.id.et_BuscaOferta);
        etPrecoMinimo = findViewById(R.id.et_preco_minimo);
        etPrecoMaximo = findViewById(R.id.et_preco_maximo);
        btAplicarFiltro = findViewById(R.id.bt_aplicar_filtro);
        ofertaDAO = new OfertaDAO(this);
        recyclerBusca = findViewById(R.id.recyclerBuscaOferta);
        recyclerBusca.setLayoutManager(new LinearLayoutManager(this));

        // Carregar todas as ofertas ao inicializar a tela
        ofertaAdapter = new OfertaAdapter(this, ofertaDAO.getAll());
        recyclerBusca.setAdapter(ofertaAdapter);

        btAplicarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar(v);
            }
        });
    }

    public void buscar(View v) {
        String titulo = buscaOferta.getText().toString();
        String precoMinimo = etPrecoMinimo.getText().toString();
        String precoMaximo = etPrecoMaximo.getText().toString();
        double min = precoMinimo.isEmpty() ? 0 : Double.parseDouble(precoMinimo);
        double max = precoMaximo.isEmpty() ? Double.MAX_VALUE : Double.parseDouble(precoMaximo);

        ofertaAdapter = new OfertaAdapter(this, ofertaDAO.buscaPorFiltro(titulo, min, max));
        recyclerBusca.setAdapter(ofertaAdapter);
    }

    public void redirecionar(View v) {
        buscaOferta.requestFocus();
    }
}