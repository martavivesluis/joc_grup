package edu.upc.dsa.control;

import edu.upc.dsa.DAOG.DAOMapa;
import edu.upc.dsa.beans.Jugador;
import edu.upc.dsa.beans.Objeto;
import edu.upc.dsa.beans.Personatge;
import edu.upc.dsa.beans.mapa.Mapa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo {
    Map<String, Personatge> mapaPersonajes;
    Map<Integer, Jugador> mapaJugadores;
    Map<String, Objeto> mapaObjetos;
    DAOMapa mapa = new DAOMapa(10,10);


    static Mundo intanceMundo;

    private Mundo()
    {
        mapaJugadores = new HashMap<Integer, Jugador>();
        mapaPersonajes = new HashMap<String, Personatge>();
        mapaObjetos = new HashMap<String, Objeto>();


        Jugador marta = new Jugador("Marta", "1234","martavivesluis@gmail.com");

        try {
            marta.select();

            marta.seleccionarPersonajes(marta);
            mapa = Mapa.miMapa(1,marta.personatges.get(0));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    static Mundo getIntanceMundo()
    {
        if(intanceMundo == null)
        {
            intanceMundo = new Mundo();
        }
        return intanceMundo;
    }

    public Objeto buscarObjeto(List<Objeto> listaObjetos, String nombreObjeto){
        for(Objeto o: listaObjetos){
            if (o.getNombre().equals(nombreObjeto)) return o;
        }
        return null;
    }

    public Map<String, Personatge> getMapaPersonajes() {
        return mapaPersonajes;
    }

    public void setMapaPersonajes(Map<String, Personatge> mapaPersonajes) {
        this.mapaPersonajes = mapaPersonajes;
    }

    public Map<Integer, Jugador> getMapaJugadores() {
        return mapaJugadores;
    }

    public void setMapaJugadores(Map<Integer, Jugador> mapaJugadores) {
        this.mapaJugadores = mapaJugadores;
    }

    public Map<String, Objeto> getMapaObjetos() {
        return mapaObjetos;
    }

    public void setMapaObjetos(Map<String, Objeto> mapaObjetos) {
        this.mapaObjetos = mapaObjetos;
    }

}


