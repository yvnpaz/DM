package esei.uvigo.es.dm.Core;

import java.util.ArrayList;
import java.util.List;

import esei.uvigo.es.dm.View.Controlador;

/**
 * Created by yvanp on 25/11/2016.
 */

public class Partido {

    private String equipoLocal;
    private String equipoVisitante;
    private int marcadorLocal;
    private int marcadorVisitante;
    private int res;

    Controlador app = new Controlador();

    public Partido(String equipoLocal, String equipoVisitante, int marcadorLocal, int marcadorVisitante) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.marcadorLocal = marcadorLocal;
        this.marcadorVisitante = marcadorVisitante;

        this.res = 0;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }


    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public int getMarcadorLocal() {
        return marcadorLocal;
    }

    public int getMarcadorVisitante() {
        return marcadorVisitante;
    }

    public void setMarcadorVisitante(int marcadorVisitante) {
        this.marcadorVisitante = marcadorVisitante;
    }

    public String getResultado() {

        String res="";
        if(this.res == 1)
        {
            res="Ganado";
        }
        else if (this.res == 0){
            res="Perdido";
        }
        else{
            res="Empate";
        }
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "Partido: \n"
                + equipoLocal + " "
                + marcadorLocal + " - "
                + equipoVisitante + " "
                + marcadorVisitante + "\n "
                + getResultado();
    }
}
