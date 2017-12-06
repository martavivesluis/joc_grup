package edu.upc.dsa;

import java.util.ArrayList;
import java.util.HashMap;

public class BDTemporal {

    HashMap<String, Jugador> mapaJugadores;
    HashMap<String, Personatge> mapaPersonajes;
    HashMap<String, Objeto> mapaObjetos;



    static BDTemporal intanceBDTemporal;

    private BDTemporal()
    {
        mapaJugadores = new HashMap<String, Jugador>();
        mapaPersonajes = new HashMap<String, Personatge>();
        mapaObjetos = new HashMap<String, Objeto>();


        Jugador juan = new Jugador("juan", "123","juan@msn.com");
        Jugador bruno = new Jugador("bruno", "123", "bruno@msn.com");
        Personatge vidente = new Personatge("vidente", 1,1,1,1);
        Personatge nodet = new Personatge("nodet", 2,2,2,2);
        Objeto objeto1 = new Objeto("pico", "herramienta", "epico", 100, 1);
        Objeto objeto2 = new Objeto("revolver", "pistola", "raro", 99, 1);
        nodet.getArrMisObjetos().add(objeto1);
        vidente.getArrMisObjetos().add(objeto2);
        bruno.getPersonatges().add(nodet);
        juan.getPersonatges().add(vidente);

        mapaJugadores.put(bruno.getNombre(), bruno);
        mapaJugadores.put(juan.getNombre(), juan);

        Jugador jugadorEmpty = new Jugador();
        mapaJugadores.put("empty", jugadorEmpty);

        Personatge personajeEmpty = new Personatge();
        mapaPersonajes.put("empty", personajeEmpty);

        Objeto objetoEmpty = new Objeto();
        objetoEmpty.setNombre("empty");
        mapaObjetos.put("empty", objetoEmpty);


    }

    static BDTemporal getDbInstancia()
    {
        if(intanceBDTemporal == null)
        {
            intanceBDTemporal = new BDTemporal();
        }
        return intanceBDTemporal;
    }


    public HashMap<String, Jugador> getMapaJugadores() {
        return mapaJugadores;
    }

    public void setMapaJugadores(HashMap<String, Jugador> mapaJugadores) {
        this.mapaJugadores = mapaJugadores;
    }

    public static BDTemporal getIntanceBDTemporal() {
        return intanceBDTemporal;
    }

    public static void setIntanceBDTemporal(BDTemporal intanceBDTemporal) {
        BDTemporal.intanceBDTemporal = intanceBDTemporal;
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
}
