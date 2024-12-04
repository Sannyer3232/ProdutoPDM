package br.edu.ifam.tads.produtos.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDProdutos extends SQLiteOpenHelper {


    public BDProdutos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql ="CREATE TABLE fornecedor("+
            "id integer primary key autoincrement,"+
            "codigo integer,"+
            "nome text, "+
            "email text," +
            "telefone text,"+
            "endereco text" +
                ")";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}