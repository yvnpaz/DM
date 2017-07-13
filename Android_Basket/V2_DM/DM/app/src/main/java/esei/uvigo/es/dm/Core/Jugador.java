package esei.uvigo.es.dm.Core;

/**
 * Created by yvanp on 25/11/2016.
 */

public class Jugador {

    private String nombre;
    private String posicion;
    private int dorsal;
    private int edad;

    public Jugador(String nombre, String posicion, int dorsal, int edad) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.dorsal = dorsal;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPosicion() { return this.posicion; }

    public void setPosicion(String pos) { posicion = pos; }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int num) {
        this.dorsal = num;
    }

    public int getEdad() { return edad; }

    public void setEdad(int edad) { this.edad = edad; }


    @Override
    public String toString() {
        return this.getNombre()+ " : Dorsal "+ this.getDorsal();
    }

}
