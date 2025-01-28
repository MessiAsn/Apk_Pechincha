package com.example.pechincha.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDpechincha extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "pechincha.db";
    private static final int DATABASE_VERSION = 1;

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
        db.execSQL(CREATE_TABLE_OFERTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS oferta");
        onCreate(db);
    }
}
