package esei.uvigo.es.dm.View;

import android.app.Application;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import esei.uvigo.es.dm.Core.Entrenamiento;
import esei.uvigo.es.dm.Core.Equipo;
import esei.uvigo.es.dm.Core.Jugador;
import esei.uvigo.es.dm.Core.Partido;
import esei.uvigo.es.dm.Core.SqlIO;
import esei.uvigo.es.dm.R;


/**
 * Created by yvanp on 01/12/2016.
 */

public class Controlador extends Application {

    private SqlIO db;
    private List<Jugador> jugadores;
    private List<Partido> partidos;
    private List<Equipo> equipo;
    private List<Entrenamiento> entrenamiento;

    @Override
    public void onCreate()
    {
        this.jugadores = new ArrayList<>();
        this.partidos = new ArrayList<>();
        this.equipo = new ArrayList<>();
        this.entrenamiento = new ArrayList<>();
        this.db = new SqlIO( this );
    }

    public SQLiteDatabase getDB()
    {
        return this.db.getWritableDatabase();
    }

    /**
     * Conseguir los atributos de las principales clases
     * @return una lista de los objetos de las principales clases.
     */
    public List<Jugador> getItemListJ()
    {
        this.leerBDJ();
        return this.jugadores;
    }

    public List<Partido> getItemListP()
    {
        this.leerBDP();
        return this.partidos;
    }

    public List<Equipo> getItemListE()
    {
        this.leerBDE();
        return this.equipo;
    }

    public List<Entrenamiento> getItemListEntreno()
    {
        this.leerBDEntreno();
        return this.entrenamiento;
    }


    /**
     * Conseguir los campos de las clases de la base de datos, introduciendolos en un array. -->LeerBDxx
     * @return una lista de los atributos de las clases.
     */
    public void leerBDJ()
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        this.jugadores.clear();

        Cursor cursor = db.rawQuery( "SELECT * FROM jugador", null );

        if ( cursor.moveToFirst() ) {
            do {
                Jugador item = new Jugador( cursor.getString( 1 ), cursor.getString( 2 ), cursor.getInt( 3 ), cursor.getInt( 4 ));
                this.jugadores.add( item );
            } while( cursor.moveToNext() );

            cursor.close();
        }

        return;
    }

    /**
     * Consigue todos los campos de la tabla especificada -->queryxx()
     * @return una lista con el valor de la base de datos.
     */
    public List<String> query(){
        SQLiteDatabase db = this.db.getReadableDatabase();
        try {
            List<String> toret = new ArrayList<String>();

            String mySQL ="select * from jugador order by id";
            Cursor c1 = db.rawQuery(mySQL, null);
            if(c1.moveToFirst()){
                do{
                    toret.add(c1.getString(0));
                }while(c1.moveToNext());
                return toret;
            }else{
                return toret;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    /**
     * Conseguir los campos de las clases de la base de datos, introduciendolos en un array. -->LeerBDxx
     * @return una lista de los atributos de las clases.
     */
    private void leerBDP()
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        this.partidos.clear();

        Cursor cursor = db.rawQuery( "SELECT * FROM partido", null );

        if ( cursor.moveToFirst() ) {
            int puntuacion = 0;
            do {
                Partido item = new Partido( cursor.getString( 1 ), cursor.getString( 2 ), cursor.getInt( 3 ), cursor.getInt( 4 ) );

                if(this.getRes( cursor.getString( 0 ) ) == 1) {
                    item.setRes(1);
                    puntuacion += 3;
                }
                else if(this.getRes( cursor.getString( 0 ) ) == 0) {
                    item.setRes(0);
                }
                else{
                    item.setRes( -1 );
                    puntuacion +=1;
                }

                this.partidos.add( item );

            } while( cursor.moveToNext() );
            this.actualizarPuntuacion( Integer.toString( puntuacion ) );
            cursor.close();
        }

        return;
    }

    /**
     * Consigue todos los campos de la tabla especificada -->queryxx()
     * @return una lista con el valor de la base de datos.
     */
    public List<String> queryP(){
        SQLiteDatabase db = this.db.getReadableDatabase();
        try {
            List<String> toret = new ArrayList<String>();
            //hard-coded SQL select with no arguments
            String mySQL ="select * from partido order by id";
            Cursor c1 = db.rawQuery(mySQL, null);
            if(c1.moveToFirst()){
                do{
                    toret.add(c1.getString(0));
                }while(c1.moveToNext());
                return toret;
            }else{
                return toret;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    /**
     * Conseguir los campos de las clases de la base de datos, introduciendolos en un array. -->LeerBDxx
     * @return una lista de los atributos de las clases.
     */
    private void leerBDE()
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        this.equipo.clear();

        Cursor cursor = db.rawQuery( "SELECT * FROM equipo", null );

        if ( cursor.moveToFirst() ) {
            do {
                Equipo item = new Equipo( cursor.getString( 1 ), cursor.getString( 2 ) );
                this.equipo.add( item );
            } while( cursor.moveToNext() );

            cursor.close();
        }

        return;
    }

    /**
     * Consigue todos los campos de la tabla especificada -->queryxx()
     * @return una lista con el valor de la base de datos.
     */
    public List<String> queryE(){
        SQLiteDatabase db = this.db.getReadableDatabase();
        try {
            List<String> toret = new ArrayList<String>();
            //hard-coded SQL select with no arguments
            String mySQL ="select * from equipo";
            Cursor c1 = db.rawQuery(mySQL, null);
            if(c1.moveToFirst()){
                do{
                    toret.add(c1.getString(0));
                }while(c1.moveToNext());
                return toret;
            }else{
                return toret;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    /**
     * Conseguir los campos de las clases de la base de datos, introduciendolos en un array. -->LeerBDxx
     * @return una lista de los atributos de las clases.
     */
    public void leerBDEntreno()
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        this.entrenamiento.clear();

        Cursor cursor = db.rawQuery( "SELECT * FROM entrenamiento", null );

        if ( cursor.moveToFirst() ) {
            do {
                Entrenamiento itemEnt = new Entrenamiento( cursor.getString( 1 ), cursor.getString( 2 ), cursor.getString( 3 ));
                if(this.getHecho( cursor.getString( 0 ) ) == 1)
                    itemEnt.setHecho( "SI" );
                else
                    itemEnt.setHecho( "NO" );
                this.entrenamiento.add( itemEnt );

            } while( cursor.moveToNext() );

            cursor.close();
        }

        return;
    }

    /**
     * Consigue todos los campos de la tabla especificada -->queryxx()
     * @return una lista con el valor de la base de datos.
     */
    public List<String> queryEntreno(){
        SQLiteDatabase db = this.db.getReadableDatabase();
        try {
            List<String> toret = new ArrayList<String>();
            //hard-coded SQL select with no arguments
            String mySQL ="select * from entrenamiento";
            Cursor c1 = db.rawQuery(mySQL, null);
            if(c1.moveToFirst()){
                do{
                    toret.add(c1.getString(0));
                }while(c1.moveToNext());
                return toret;
            }else{
                return toret;
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    /*
    *   Se añaden datos a la base de datos
    */
    public void addJugador(String nombre, String posJug, int dorsal, int edad)
    {
        SQLiteDatabase db = this.getDB();

        try {
            db.beginTransaction();
            // Actualizar la base de datos
            db.execSQL( "INSERT OR IGNORE INTO jugador(nombre, posJug, dorsal, edad, favorito, lesionado, titular ) VALUES(?, ?, ?, ?, ?, ?, ?)",
                    new String[]{ nombre, posJug, Integer.toString( dorsal ), Integer.toString( edad ), Integer.toString( 0 ), Integer.toString( 0 ), Integer.toString( 0 ) } );

            // Actualizar la lista
            Jugador item = new Jugador( nombre, posJug, dorsal, edad );
            this.jugadores.add( item );
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }

    public void addPartido(String equipoLocal, String equipoVisitante, int marcadorLocal, int marcadorVisitante)
    {
        SQLiteDatabase db = this.getDB();

        try {
            db.beginTransaction();
            // Actualizar la base de datos
            db.execSQL( "INSERT OR IGNORE INTO partido(equipoLocal, equipoVisitante, marcadorLocal, marcadorVisitante, resultado ) VALUES(?, ?, ?, ?, ?)",
                    new String[]{ equipoLocal, equipoVisitante, Integer.toString( marcadorLocal ), Integer.toString( marcadorVisitante ), Integer.toString( 0 ) } );

            // Actualizar la lista
            Partido item = new Partido( equipoLocal, equipoVisitante, marcadorLocal, marcadorVisitante);
            this.partidos.add( item );
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }

    public void addEquipo(String nombre, String tipo)
    {
        SQLiteDatabase db = this.getDB();

        try {
            db.beginTransaction();
            // Actualizar la base de datos
            db.execSQL( "INSERT OR IGNORE INTO equipo(nombre, tipoEquipo, puntuacion) VALUES(?, ?, ?)",
                    new String[]{ nombre, tipo, Integer.toString( 0 ) } );

            // Actualizar la lista
            Equipo item = new Equipo( nombre, tipo );
            this.equipo.add( item );
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }

    public void addEntreno(String nombreEntreno, String tipoEntreno, String descripcionEntreno)
    {
        SQLiteDatabase db = this.getDB();

        try {
            db.beginTransaction();
            // Actualizar la base de datos
            db.execSQL( "INSERT OR IGNORE INTO entrenamiento(nombre, tipoEntreno, descripcion, realizado ) VALUES(?, ?, ?, ?)",
                    new String[]{ nombreEntreno, tipoEntreno, descripcionEntreno, Integer.toString( 0 ) } );

            // Actualizar la lista
            Entrenamiento itemEntreno = new Entrenamiento( nombreEntreno, tipoEntreno, descripcionEntreno );
            this.entrenamiento.add( itemEntreno );
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }

    /*
    *   Se modifican datos de la base de datos.
     */

    public void modifyJugador(int pos, String nombre, String posJug, int dorsal, int edad)
    {
        // Actualizar lista
        this.leerBDJ();

        // Modificar lista
        Jugador item = new Jugador( nombre, posJug, dorsal, edad );
        this.jugadores.set( pos, item );

        // Actualizar base de datos
        SQLiteDatabase db = this.getDB();
        try {
            db.beginTransaction();
            db.execSQL( "DELETE FROM jugador" );
            for(Jugador it: this.jugadores) {
                db.execSQL( "INSERT INTO jugador(nombre, posJug, dorsal, edad, favorito, lesionado, titular) VALUES(?, ?, ?, ?, ?, ?, ?)",
                        new String[]{ it.getNombre(), it.getPosicion(), Integer.toString( it.getDorsal() ), Integer.toString( it.getEdad() ), Integer.toString( 0 ), Integer.toString( 0 ), Integer.toString( 0 )  } );
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }


    public void modifyPartido(int pos, String equipoLocal, String equipoVisitante, int marcadorLocal, int marcadorVisitante)
    {
        // Actualizar lista
        this.leerBDP();

        // Modificar lista
        Partido item = new Partido( equipoLocal, equipoVisitante, marcadorLocal, marcadorVisitante );
        this.partidos.set( pos, item );

        // Actualizar base de datos
        SQLiteDatabase db = this.getDB();
        try {
            db.beginTransaction();
            db.execSQL( "DELETE FROM partido" );
            for(Partido it: this.partidos) {
                db.execSQL( "INSERT INTO partido(equipoLocal, equipoVisitante, marcadorLocal, marcadorVisitante, resultado ) VALUES(?, ?, ?, ?, ?)",
                        new String[]{ it.getEquipoLocal(), it.getEquipoVisitante(), Integer.toString( it.getMarcadorLocal() ), Integer.toString( it.getMarcadorVisitante() ), Integer.toString( 0 ) } );
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }

    public void modifyEntrenamiento(int pos, String nombreEntreno, String tipoEntreno, String descripcionEntreno)
    {
        // Actualizar lista
        this.leerBDEntreno();

        // Modificar lista
        Entrenamiento itemEnt = new Entrenamiento( nombreEntreno, tipoEntreno, descripcionEntreno );
        this.entrenamiento.set( pos, itemEnt );

        // Actualizar base de datos
        SQLiteDatabase db = this.getDB();
        try {
            db.beginTransaction();
            db.execSQL( "DELETE FROM entrenamiento" );
            for(Entrenamiento it: this.entrenamiento) {
                db.execSQL( "INSERT INTO entrenamiento(nombre, tipoEntreno, descripcion, realizado ) VALUES(?, ?, ?, ?)",
                        new String[]{ it.getNombre(), it.getTipo(), it.getDescripcion(), Integer.toString( 0 ) } );
            }
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }

        return;
    }

    public void modifyEquipo(String pos, String nombre, String tipo)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE equipo SET nombre=?, tipoEquipo=? WHERE id=?", new String[] { nombre, tipo, pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }
    }

    /*
    *   Se borran datos.
     */
    public void deleteP(String id)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            int toret = dbSql.delete("partido", "id="+id, null );
            Toast.makeText(this, "Partido borrado con id " + id + " linea "+toret, Toast.LENGTH_LONG).show();
            dbSql.setTransactionSuccessful();
        } finally {
            dbSql.endTransaction();
        }
    }

    public void deleteJ(String id)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            int toret = dbSql.delete("jugador", "id="+id, null );
            Toast.makeText(this, "Jugador borrado con id " + id + " linea "+toret, Toast.LENGTH_LONG).show();
            dbSql.setTransactionSuccessful();
        } finally {
            dbSql.endTransaction();
        }
    }

    public void deleteEnt(String id)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            int toret = dbSql.delete("entrenamiento", "id="+id, null );
            Toast.makeText(this, "Entrenamiento borrado con id " + id + " linea "+toret, Toast.LENGTH_LONG).show();
            dbSql.setTransactionSuccessful();
        } finally {
            dbSql.endTransaction();
        }
    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void addFavorito(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE jugador SET favorito='1' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void quitarFavorito(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE jugador SET favorito='0' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Consigue el valor favorito de la tabla especificada -->queryxx()
     * @return un entero con el valor de la base de datos.
     */
    public int getFavorito(String pos)
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        String itemfav="";
        String mySQL = "SELECT favorito FROM jugador WHERE id=?";
        Cursor cursor = db.rawQuery(mySQL, new String[]{ pos });

        if ( cursor.moveToFirst() ) {
            do {
                itemfav = cursor.getString( 0 );
            } while( cursor.moveToNext() );

            cursor.close();
        }
        return Integer.parseInt( itemfav );
    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void addLesionado(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE jugador SET lesionado='1' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void quitarLesionado(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE jugador SET lesionado='0' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Consigue el valor lesionado de la tabla especificada -->queryxx()
     * @return un entero con el valor de la base de datos.
     */
    public int getLesionado(String pos)
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        String itemLes="";
        String mySQL = "SELECT lesionado FROM jugador WHERE id=?";
        Cursor cursor = db.rawQuery(mySQL, new String[]{ pos });

        if ( cursor.moveToFirst() ) {
            do {
                itemLes = cursor.getString( 0 );
            } while( cursor.moveToNext() );

            cursor.close();
        }
        return Integer.parseInt( itemLes );
    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void addTitular(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE jugador SET titular='1' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void quitarTiutlar(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE jugador SET titular='0' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Consigue el valor lesionado de la tabla especificada -->queryxx()
     * @return un entero con el valor de la base de datos.
     */
    public int getTitular(String pos)
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        String itemTit="";
        String mySQL = "SELECT titular FROM jugador WHERE id=?";
        Cursor cursor = db.rawQuery(mySQL, new String[]{ pos });

        if ( cursor.moveToFirst() ) {
            do {
                itemTit = cursor.getString( 0 );
            } while( cursor.moveToNext() );

            cursor.close();
        }
        return Integer.parseInt( itemTit );
    }

    /**
     * Se suman todas las filas de la tabla equipo para saber si hay mas de 1 equipo
     * @return un entero con el valor de la base de datos.
     */
    public int getHayEquipo()
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        String itemLes="";
        String mySQL = "SELECT count(id) FROM equipo";
        Cursor cursor = db.rawQuery(mySQL, null);

        if ( cursor.moveToFirst() ) {
            do {
                itemLes = cursor.getString( 0 );
            } while( cursor.moveToNext() );

            cursor.close();
        }
        return Integer.parseInt( itemLes );
    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void ganado(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE partido SET resultado='1' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void perdido(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE partido SET resultado='0' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void empate(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE partido SET resultado='-1' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }

    }

    /**
     * Consigue el resultado de la tabla especificada -->queryxx()
     * @return un entero con el valor de la base de datos.
     */
    public int getRes(String pos)
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        String itemRes="";
        String mySQL = "SELECT resultado FROM partido WHERE id=?";
        Cursor cursor = db.rawQuery(mySQL, new String[] { pos });

        if ( cursor.moveToFirst() ) {
            do {
                itemRes = cursor.getString( 0 );
            } while( cursor.moveToNext() );

            cursor.close();
        }
        return Integer.parseInt( itemRes );
    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void addEntrenamiento(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE entrenamiento SET realizado='1' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }
    }

    /**
     * Se modifica el campo de la tabla especificada.
     * @return un registro de la base de datos.
     */
    public void quitarEntrenamiento(String pos)
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE entrenamiento SET realizado='0' WHERE id=?", new String[] { pos });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }
    }

    /**
     * Consigue si se ha realizado el entrenamiento de la tabla especificada -->queryxx()
     * @return un entero con el valor de la base de datos.
     */
    public int getHecho(String pos)
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        String itemDo="";
        String mySQL = "SELECT realizado FROM entrenamiento WHERE id=?";
        Cursor cursor = db.rawQuery(mySQL, new String[]{ pos });

        if ( cursor.moveToFirst() ) {
            do {
                itemDo = cursor.getString( 0 );
            } while( cursor.moveToNext() );

            cursor.close();
        }
        return Integer.parseInt( itemDo );
    }

    /**
     * Consigue la puntuacion del equipo de la tabla especificada -->queryxx()
     * @return un entero con el valor de la base de datos.
     */
    public int getPuntuacion(String pos)
    {
        SQLiteDatabase db = this.db.getReadableDatabase();
        String itemPunt="";
        String mySQL = "SELECT puntuacion FROM equipo WHERE id=?";
        Cursor cursor = db.rawQuery(mySQL, new String[]{ pos });

        if ( cursor.moveToFirst() ) {
            do {
                itemPunt = cursor.getString( 0 );
            } while( cursor.moveToNext() );

            cursor.close();
        }
        return Integer.parseInt( itemPunt );
    }

    /**
     * Se actualiza la puntuacion del equipo.
     * @return un registro de la base de datos.
     */
    public void actualizarPuntuacion( String puntuacion )
    {
        SQLiteDatabase dbSql = db.getWritableDatabase();

        try {
            dbSql.beginTransaction();
            //Actualizar un registro
            dbSql.execSQL("UPDATE equipo SET puntuacion=? WHERE id=1", new String[] { puntuacion });
            dbSql.setTransactionSuccessful();
        }
        finally {
            dbSql.endTransaction();
        }
    }

}


