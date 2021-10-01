package com.example.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int database_version = 1;
    public static final String database_nombre = "prueba7.db";
    public static final String table_contactos = "t_contactos";



    public DbHelper(@Nullable Context context) {
        super(context, database_nombre, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + table_contactos + "(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT NOT NULL," +
                "TELEFONO TEXT NOT NULL," +
                "CORREO_ELECTRONICO TEXT)");
    }

    //El metodo OnUpgrade se ejecuta cuando cambie la version de la BD, y la version cambia de acuerdo a las modificaciones de la estructura
    //de la base de datos.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + table_contactos);
        onCreate(sqLiteDatabase);
    }
}
