package edu.upc.dsa;

import edu.upc.dsa.DAOG.DAO;

public class relacio extends DAO{
    public int idJugador;
    public int idPersonatge;

    public relacio()
    {
        this.hasId = false;
        this.idAutogen = true;
    }
    public relacio(int idJugador, int idPersonaje){
        this.idJugador = idJugador;
        this.idPersonatge = idPersonaje;
        this.hasId = false;
        this.idAutogen = true;
    }
    public int getIdJugador() {
        return idJugador;
    }
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    public int getIdPersonaje() {
        return idPersonatge;
    }
    public void setIdPersonaje(int idPersonaje) {
        this.idPersonatge = idPersonaje;
    }
}
