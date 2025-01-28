package com.example.pechincha.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.pechincha.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private SQLiteDatabase db;

    public CategoriaDAO(Context context) {
        BDpechincha dbHelper = new BDpechincha(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insert(Categoria categoria) {
        ContentValues values = new ContentValues();
        values.put("nome", categoria.getNome());
        db.insert("categoria", null, values);
    }

    public void update(Long id, Categoria categoria) {
        ContentValues values = new ContentValues();
        values.put("nome", categoria.getNome());
        db.update("categoria", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public void delete(Long id) {
        db.delete("categoria", "id = ?", new String[]{String.valueOf(id)});
    }

    public List<Categoria> getAll() {
        List<Categoria> categorias = new ArrayList<>();
        Cursor cursor = db.query("categoria", null, null, null, null, null, "nome");
        while (cursor.moveToNext()) {
            Categoria categoria = new Categoria();
            categoria.setId(cursor.getLong(cursor.getColumnIndex("id")));
            categoria.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            categorias.add(categoria);
        }
        cursor.close();
        return categorias;
    }

    public Categoria getById(Long id) {
        Cursor cursor = db.query("categoria", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            Categoria categoria = new Categoria();
            categoria.setId(cursor.getLong(cursor.getColumnIndex("id")));
            categoria.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cursor.close();
            return categoria;
        }
        cursor.close();
        return null;
    }
}