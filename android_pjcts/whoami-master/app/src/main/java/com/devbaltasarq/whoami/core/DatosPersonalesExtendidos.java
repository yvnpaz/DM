package com.devbaltasarq.whoami.core;

/**
 * Created by yvanp on 19/10/2016.
 */

public class DatosPersonalesExtendidos {

    private String nombre;
    private String email;
    private String direccion;

    /**
     * Construye un nuevo objeto de datos personales
     * a partir de los datos individuales
     * @param n El nombre, como texto.
     * @param a La dirección, como texto.
     * @param e El e.mail, como texto.
*/

    final public String getNombre() {
        return nombre;
    }

    final public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    final public String getEmail() {
        return email;
    }

    final public void setEmail(String email) {
        this.email = email;
    }

    final public String getDireccion() {
        return direccion;
    }

    final public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
