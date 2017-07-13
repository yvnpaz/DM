package esei.uvigo.es.dm.Core;

/**
 * Created by yvanp on 04/01/2017.
 */

public class Equipo {

    private String nombre;
    private String tipo;
    private int puntuacion;

    public Equipo(String nombre, String tipo)
    {
        this.nombre = nombre;
        this.tipo = tipo;
        this.puntuacion = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }



    @Override
    public String toString() {
        return "Equipo: " + getPuntuacion();
    }
}
