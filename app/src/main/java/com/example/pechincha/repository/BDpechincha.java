package com.example.pechincha.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDpechincha extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pechincha.db";
    private static final int DATABASE_VERSION = 2; // Atualize a versão do banco de dados

    public BDpechincha(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_OFERTA = "CREATE TABLE oferta (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "preco REAL, " +
                "cupom TEXT)";

        String CREATE_TABLE_CATEGORIA = "CREATE TABLE categoria (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL)";

        String CREATE_TABLE_USUARIO = "CREATE TABLE usuario (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "senha TEXT NOT NULL)";

        db.execSQL(CREATE_TABLE_OFERTA);
        db.execSQL(CREATE_TABLE_CATEGORIA);
        db.execSQL(CREATE_TABLE_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            String CREATE_TABLE_CATEGORIA = "CREATE TABLE categoria (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL)";

            String CREATE_TABLE_USUARIO = "CREATE TABLE usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "email TEXT NOT NULL, " +
                    "senha TEXT NOT NULL)";

            db.execSQL(CREATE_TABLE_CATEGORIA);
            db.execSQL(CREATE_TABLE_USUARIO);
        }
    }
}