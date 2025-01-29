package com.example.pechincha;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pechincha.recycler.CategoriaAdapter;
import com.example.pechincha.repository.CategoriaDAO;
import com.example.pechincha.model.Categoria;

import java.util.List;

public class CategoriasActivity extends AppCompatActivity {

    private RecyclerView recyclerCategorias;
    private CategoriaAdapter categoriaAdapter;
    private CategoriaDAO categoriaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        recyclerCategorias = findViewById(R.id.recyclerCategorias);
        recyclerCategorias.setLayoutManager(new LinearLayoutManager(this));

        categoriaDAO = new CategoriaDAO(this);
        List<Categoria> categorias = categoriaDAO.getAll();
        categoriaAdapter = new CategoriaAdapter(this, categorias);
        recyclerCategorias.setAdapter(categoriaAdapter);
    }

    public void verOfertasPorCategoria(Categoria categoria) {
        Intent intent = new Intent(this, BuscarOferta.class);
        intent.putExtra("categoria", categoria.getNome());
        startActivity(intent);
    }
}