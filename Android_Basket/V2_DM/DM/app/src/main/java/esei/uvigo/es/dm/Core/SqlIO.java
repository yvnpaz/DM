package esei.uvigo.es.dm.Core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yvanp on 25/11/2016.
 */

public class SqlIO extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TeamSport";
    private static final int DATABASE_VERSION = 2;

    public SqlIO(Context context)
    {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.beginTransaction();
        try {

            db.execSQL("CREATE TABLE IF NOT EXISTS jugador("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre string(255) NOT NULL,"
                    + "posJug string(255) NOT NULL,"
                    + "dorsal int NOT NULL,"
                    + "edad int NOT NULL,"
                    + "favorito boolean NOT NULL,"
                    + "lesionado boolean NOT NULL,"
                    + "titular boolean NOT NULL"
                    + ")" );
            db.execSQL("CREATE TABLE IF NOT EXISTS partido("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "equipoLocal string(255) NOT NULL,"
                    + "equipoVisitante string(255) NOT NULL,"
                    + "marcadorLocal int NOT NULL,"
                    + "marcadorVisitante int NOT NULL,"
                    + "resultado int NOT NULL"
                    + ")" );
            db.execSQL("CREATE TABLE IF NOT EXISTS equipo("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre string(255) NOT NULL,"
                    + "tipoEquipo string(255) NOT NULL,"
                    + "puntuacion int NOT NULL"
                    + ")" );
            db.execSQL("CREATE TABLE IF NOT EXISTS entrenamiento("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre string(255) NOT NULL,"
                    + "tipoEntreno string(255) NOT NULL,"
                    + "descripcion string(255) NOT NULL,"
                    + "realizado boolean NOT NULL"
                    + ")" );
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        Log.w( SqlIO.class.getName(),
                "Upgrading database from version "
                        + oldVersion
                        + " to " + newVersion
                        + ", which will destroy all old data" );

        try {
            db.beginTransaction();
            db.execSQL("DROP TABLE IF EXISTS jugador");
            db.execSQL("DROP TABLE IF EXISTS partido");
            db.execSQL("DROP TABLE IF EXISTS equipo");
            db.execSQL("DROP TABLE IF EXISTS entrenamiento");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }

        this.onCreate( db );
    }
}
