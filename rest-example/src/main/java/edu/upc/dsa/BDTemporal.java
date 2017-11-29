package edu.upc.dsa;

import java.util.HashMap;

/**
 * Created by nodet on 29/11/17.
 */
public class BDTemporal {

    HashMap<String, Jugador> mapaJugadores;
    HashMap<String, Personatge> mapaPersonajes;
    HashMap<String, Objeto> mapaObjetos;

    static BDTemporal instanciaBD;

    private BDTemporal(){
        mapaJugadores = new HashMap<String, Jugador>();
        mapaPersonajes = new HashMap<String, Personatge>();
        mapaObjetos = new HashMap<String, Objeto>();
        Jugador bruno = new Jugador("bruno", "123", "email1");
        mapaJugadores.put(bruno.getNom(), bruno);
    }



    public HashMap<String, Jugador> getMapaJugadores() {
        return mapaJugadores;
    }

    public void setMapaJugadores(HashMap<String, Jugador> mapaJugadores) {
        this.mapaJugadores = mapaJugadores;
    }

    public HashMap<String, Personatge> getMapaPersonajes() {
        return mapaPersonajes;
    }

    public void setMapaPersonajes(HashMap<String, Personatge> mapaPersonajes) {
        this.mapaPersonajes = mapaPersonajes;
    }

    public HashMap<String, Objeto> getMapaObjetos() {
        return mapaObjetos;
    }

    public void setMapaObjetos(HashMap<String, Objeto> mapaObjetos) {
        this.mapaObjetos = mapaObjetos;
    }

    public static BDTemporal getInstanciaBD() {
        return instanciaBD;
    }

    public static void setInstanciaBD(BDTemporal instanciaBD) {
        BDTemporal.instanciaBD = instanciaBD;
    }
}
