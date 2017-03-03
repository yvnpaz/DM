package com.baltasarq.listacompra4.Core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Represents the database access.
 * Created by baltasarq on 15/11/16.
 */

public class SqlIO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ListaCompra";
    private static final int DATABASE_VERSION = 1;

    public SqlIO(Context context)
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try {
            db.beginTransaction();
            db.execSQL( "CREATE TABLE IF NOT EXISTS compra( "
                            + "nombre string(255) NOT NULL, "
                            + "num int NOT NULL)");
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.i(  "ListaCompra4.SqlIo",
                "Actualizando BBDD de version " + oldVersion + " a la " + newVersion );

        try {
            db.beginTransaction();
            db.execSQL( "DROP TABLE IF EXISTS compra");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        this.onCreate( db );
    }
}
