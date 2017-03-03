package com.baltasarq.listacompra4.View;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baltasarq.listacompra4.Core.Item;
import com.baltasarq.listacompra4.Core.SqlIO;

import java.util.ArrayList;
import java.util.List;

/**
 * Sustituye a la clase Application por defecto.
 * Created by baltasarq on 2/11/16.
 */

public class ListaCompra4App extends Application {
    private SqlIO db;
    private List<Item> items;

    @Override
    public void onCreate()
    {
        this.items = new ArrayList<>();
        this.db = new SqlIO( this );
    }

    public SQLiteDatabase getDB()
    {
        return this.db.getWritableDatabase();
    }

    /**
     * Gets the list of items
     * @return the list of items, as a List<Item>.
     */
    public List<Item> getItemList()
    {
        this.leerBD();
        return this.items;
    }

    private void leerBD()
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        this.items.clear();

        Cursor cursor = db.rawQuery( "SELECT * FROM compra", null );

        if ( cursor.moveToFirst() ) {
            do {
                Item item = new Item( cursor.getString( 0 ) );
                item.setNum( cursor.getInt( 1 ) );
                this.items.add( item );
            } while( cursor.moveToNext() );

            cursor.close();
        }

        return;
    }

    public void addItem(String nombre, int num)
    {
        SQLiteDatabase db = this.getDB();

        try {
            db.beginTransaction();
            // Actualizar la base de datos
            db.execSQL( "INSERT INTO compra(nombre, num) VALUES (?, ?)",
                        new String[]{ nombre, Integer.toString( num ) } );

            // Actualizar la lista
            Item item = new Item( nombre );
            item.setNum( num );
            this.items.add( item );
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }

    public void modifyItem(int pos, String nombre, int num)
    {
        // Actualizar lista
        this.leerBD();

        // Modificar lista
        Item item = new Item( nombre );
        item.setNum( num );
        this.items.set( pos, item );

        // Actualizar base de datos
        SQLiteDatabase db = this.getDB();
        try {
            db.beginTransaction();
            db.execSQL( "DELETE FROM compra" );
            for(Item it: this.items) {
                db.execSQL( "INSERT INTO compra(nombre, num) VALUES(?, ?)",
                            new String[]{ it.getNombre(), Integer.toString( it.getNum() ) } );
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }
}
