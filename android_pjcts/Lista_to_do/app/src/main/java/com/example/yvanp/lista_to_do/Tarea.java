package com.example.yvanp.lista_to_do;

/**
 * Created by yvanp on 05/10/2016.
 */

public class Tarea {
    private String nombreTarea;
    private int dia;
    private int mes;
    private int anyo;

    public String getNombreTarea()
    { return nombreTarea; }
    public void setNombreTarea(String nombreT)
    { this.nombreTarea = nombreT; }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnyo() {
        return anyo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "nombreTarea='" + nombreTarea + '\'' +
                ", dia=" + dia +
                ", mes=" + mes +
                ", anyo=" + anyo +
                '}';
    }
}
