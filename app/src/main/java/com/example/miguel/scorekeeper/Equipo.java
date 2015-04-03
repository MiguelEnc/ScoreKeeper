package com.example.miguel.scorekeeper;

/**
 * Created by miguel on 04/02/15.
 */
public class Equipo {
    private String nombre;
    private String descripcion;

    public Equipo(){}

    public Equipo(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Sets nombre.
     *
     * @param  nombre
     */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Gets the nombre.
     *
     * @return nombre
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Sets descripcion.
     *
     * @param  descripcion
     */
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    /**
     * Gets the descripcion.
     *
     * @return descripcion
     */
    public String getDescripcion(){
        return this.descripcion;
    }
}
