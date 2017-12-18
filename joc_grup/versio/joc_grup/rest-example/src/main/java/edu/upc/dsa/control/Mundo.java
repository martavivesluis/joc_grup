package edu.upc.dsa.control;

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
    Mapa mapa = new Mapa(9,9);


    static Mundo intanceMundo;

    private Mundo()
    {
        mapaJugadores = new HashMap<Integer, Jugador>();
        mapaPersonajes = new HashMap<String, Personatge>();
        mapaObjetos = new HashMap<String, Objeto>();


        Jugador juan = new Jugador("juan", "123","juan@msn.com");
        Jugador bruno = new Jugador("bruno", "123", "bruno@msn.com");



        Personatge vidente = new Personatge("vidente", 1,1,1,1);
        Personatge nodet = new Personatge("nodet", 2,2,2,2);



        mapa.putElement(2,2,vidente);
        mapa.putElement(4,4,nodet);


        bruno.getPersonatges().add(nodet);
        juan.getPersonatges().add(vidente);

        mapaJugadores.put(bruno.getId(), bruno);
        mapaJugadores.put(juan.getId(), juan);

        Jugador jugadorEmpty = new Jugador();
        mapaJugadores.put(jugadorEmpty.getId(), jugadorEmpty);

        Personatge personajeEmpty = new Personatge();
        mapaPersonajes.put("empty", personajeEmpty);
        mapaPersonajes.put("vidente", vidente);
        mapaPersonajes.put("nodet", nodet);

        Objeto objetoEmpty = new Objeto();
        objetoEmpty.setNombre("empty");
        mapaObjetos.put("empty", objetoEmpty);



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


