package com.example.pechincha.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.pechincha.model.Usuario;

public class UsuarioDAO {

    private SQLiteDatabase db;

    public UsuarioDAO(Context context) {
        BDpechincha dbHelper = new BDpechincha(context);
        db = dbHelper.getWritableDatabase();
    }

    public void insert(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        db.insert("usuario", null, values);
    }

    public Usuario getUsuario(String email, String senha) {
        Cursor cursor = db.query("usuario", null, "email = ? AND senha = ?", new String[]{email, senha}, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int nomeIndex = cursor.getColumnIndex("nome");
            int emailIndex = cursor.getColumnIndex("email");
            int senhaIndex = cursor.getColumnIndex("senha");

            if (idIndex == -1 || nomeIndex == -1 || emailIndex == -1 || senhaIndex == -1) {
                Log.e("UsuarioDAO", "Uma ou mais colunas não existem no cursor.");
                cursor.close();
                return null;
            }

            Usuario usuario = new Usuario();
            usuario.setId(cursor.getLong(idIndex));
            usuario.setNome(cursor.getString(nomeIndex));
            usuario.setEmail(cursor.getString(emailIndex));
            usuario.setSenha(cursor.getString(senhaIndex));
            cursor.close();
            return usuario;
        }
        cursor.close();
        return null;
    }

    public void update(Long id, Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        db.update("usuario", values, "id = ?", new String[]{String.valueOf(id)});
    }

    public Usuario getUsuarioById(Long id) {
        Cursor cursor = db.query("usuario", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex("id");
            int nomeIndex = cursor.getColumnIndex("nome");
            int emailIndex = cursor.getColumnIndex("email");
            int senhaIndex = cursor.getColumnIndex("senha");

            if (idIndex == -1 || nomeIndex == -1 || emailIndex == -1 || senhaIndex == -1) {
                Log.e("UsuarioDAO", "Uma ou mais colunas não existem no cursor.");
                cursor.close();
                return null;
            }

            Usuario usuario = new Usuario();
            usuario.setId(cursor.getLong(idIndex));
            usuario.setNome(cursor.getString(nomeIndex));
            usuario.setEmail(cursor.getString(emailIndex));
            usuario.setSenha(cursor.getString(senhaIndex));
            cursor.close();
            return usuario;
        }
        cursor.close();
        return null;
    }
}