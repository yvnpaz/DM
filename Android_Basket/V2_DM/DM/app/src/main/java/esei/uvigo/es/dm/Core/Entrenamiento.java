package esei.uvigo.es.dm.Core;

/**
 * Created by yvanp on 05/01/2017.
 */

public class Entrenamiento {


    private String nombre;
    private String tipo;
    private String descripcion;
    private String hecho;

    public Entrenamiento(String nombre, String tipo, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;

        this.hecho ="NO";
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getHecho() {
        return hecho;
    }

    public void setHecho(String hecho) {

        if(hecho == "SI")
        {
            this.hecho = "SI";
        }
        else{
            this.hecho = "NO";
        }

    }

    @Override
    public String toString() {
        return  "Entrenamiento: " + nombre +
                " - tipo: " + tipo +
                " - descripcion: " + descripcion +
                "\nRealizado: "+getHecho();
    }
}
