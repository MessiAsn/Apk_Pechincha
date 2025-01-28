package com.example.pechincha.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.pechincha.model.Oferta;

public class OfertaDAO {

    private SQLiteDatabase db;

    public OfertaDAO(Context context) {
        BDpechincha dbHelper = new BDpechincha(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insert(Oferta oferta) {
        ContentValues values = new ContentValues();
        values.put("titulo", oferta.getTitulo());
        values.put("preco", oferta.getPreco());
        values.put("cupom", oferta.getCupom());
        db.insert("oferta", null, values);
    }

    public void update(Long id, Oferta oferta) {
        ContentValues values = new ContentValues();
        values.put("titulo", oferta.getTitulo());
        values.put("preco", oferta.getPreco());
        values.put("cupom", oferta.getCupom());
        db.update("oferta", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public void delete(Long id) {
        db.delete("oferta", "id = ?", new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public List<Oferta> getAll() {
        List<Oferta> ofertas = new ArrayList<>();
        Cursor cursor = db.query("oferta", null, null, null, null, null, "titulo");
        while (cursor.moveToNext()) {
            Oferta oferta = new Oferta();
            oferta.setId(cursor.getLong(cursor.getColumnIndex("id")));
            oferta.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            oferta.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
            oferta.setCupom(cursor.getString(cursor.getColumnIndex("cupom")));
            ofertas.add(oferta);
        }
        cursor.close();
        return ofertas;
    }

    @SuppressLint("Range")
    public Oferta getById(Long id) {
        Cursor cursor = db.query("oferta", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            Oferta oferta = new Oferta();
            oferta.setId(cursor.getLong(cursor.getColumnIndex("id")));
            oferta.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            oferta.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
            oferta.setCupom(cursor.getString(cursor.getColumnIndex("cupom")));
            cursor.close();
            return oferta;
        }
        cursor.close();
        return null;
    }

    public Boolean ofertaExiste(String titulo){
        String consulta = "SELECT titulo FROM oferta where Lower(titulo) = ?";
        String[] selectionArgs = {titulo.toLowerCase(Locale.ROOT)};
        Cursor cursor = db.rawQuery(consulta, selectionArgs);

        if(cursor.moveToFirst()) return true;

        return false;
    }

    @SuppressLint("Range")
    public List<Oferta> buscaTitulo(String titulo){
        List<Oferta> ofertas = new ArrayList<>();
        String consulta = "SELECT * FROM oferta where Lower(titulo) like ?";
        String[] selectionArgs = {"%" + titulo.toLowerCase(Locale.ROOT) + "%"};

        Cursor cursor = db.rawQuery(consulta, selectionArgs);
        while (cursor.moveToNext()) {
            Oferta oferta = new Oferta();
            oferta.setId(cursor.getLong(cursor.getColumnIndex("id")));
            oferta.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            oferta.setPreco(cursor.getDouble(cursor.getColumnIndex("preco")));
            oferta.setCupom(cursor.getString(cursor.getColumnIndex("cupom")));
            ofertas.add(oferta);
        }
        cursor.close();
        return ofertas;
    }

}
