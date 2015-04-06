package com.example.miguel.scorekeeper;

/**
 * Created by miguel on 04/02/15.
 */
public class Partida {
    private int id = 0;
    private String equipo_A;
    private String equipo_B;
    private String juego;
    private int puntaje_equipo_A;
    private int puntaje_equipo_B;

    public Partida(){}

    public Partida(Integer id, String Eq_A, String Eq_B, String juego, Integer puntos_A, Integer puntos_B){
        this.id = id;
        this.equipo_A = Eq_A;
        this.equipo_B = Eq_B;
        this.juego = juego;
        this.puntaje_equipo_A = puntos_A;
        this.puntaje_equipo_B = puntos_B;
    }

    /**
     * Sets id.
     *
     * @param id
     */
    public void setID(Integer id){
        this.id = id;
    }

    /**
     * Gets the id.
     *
     * @return id
     */
    public Integer getID(){
        return this.id;
    }


    /**
     * Sets Equipo_A.
     *
     * @param  Eq_A
     */
    public void setEquipo_A(String Eq_A){
        this.equipo_A = Eq_A;
    }

    /**
     * Gets the Equipo_A.
     *
     * @return equipo_A
     */
    public String getEquipo_A(){
        return this.equipo_A;
    }


    /**
     * Sets Equipo_B.
     *
     * @param  Eq_B
     */
    public void setEquipo_B(String Eq_B){
        this.equipo_B = Eq_B;
    }

    /**
     * Gets the Equipo_B.
     *
     * @return equipo_B
     */
    public String getEquipo_B(){
        return this.equipo_B;
    }


    /**
     * Sets Juego.
     *
     * @param  juego
     */
    public void setJuego(String juego){
        this.juego = juego;
    }

    /**
     * Gets the Juego.
     *
     * @return juego
     */
    public String getJuego(){
        return this.juego;
    }


    /**
     * Sets puntaje del equipo A.
     *
     * @param  puntos
     */
    public void setPuntaje_equipo_A(Integer puntos){
        this.puntaje_equipo_A = puntos;
    }

    /**
     * Gets puntaje del equipo A.
     *
     * @return puntos
     */
    public Integer getPuntajeEquipoA(){
        return this.puntaje_equipo_A;
    }


    /**
     * Sets puntaje del equipo B.
     *
     * @param  puntos
     */
    public void setPuntaje_equipo_B(Integer puntos){
        this.puntaje_equipo_B = puntos;
    }

    /**
     * Gets puntaje del equipo B.
     *
     * @return puntos
     */
    public Integer getPuntajeEquipoB(){
        return this.puntaje_equipo_B;
    }
}
