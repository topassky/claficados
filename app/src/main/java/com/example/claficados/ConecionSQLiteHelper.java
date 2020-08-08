package com.example.claficados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.claficados.oi.utilities;

///import com.example.ciclista_actual.utilities.Utilities;

public class ConecionSQLiteHelper extends SQLiteOpenHelper {

    public ConecionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilities.createTable0);
        db.execSQL(utilities.createTable2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS  "+utilities.tblmuestra);
        db.execSQL("DROP TABLE IF EXISTS  "+utilities.tblconfig);
        onCreate(db);
    }
}