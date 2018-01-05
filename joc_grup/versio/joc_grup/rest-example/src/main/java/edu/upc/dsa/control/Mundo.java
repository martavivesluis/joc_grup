package edu.upc.dsa.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upc.dsa.DAOG.DAOMapa;
import edu.upc.dsa.DAOG.relacioPersonatgeObjecte;
import edu.upc.dsa.beans.*;
import edu.upc.dsa.beans.mapa.Mapa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo {
    Map<String, Personatge> mapaPersonajes;
    Map<Integer, Jugador> mapaJugadores;
    Map<String, Objeto> mapaObjetos;
    DAOMapa mapa = new DAOMapa(10, 10);


    static Mundo intanceMundo;

    private Mundo() {
        mapaJugadores = new HashMap<Integer, Jugador>();
        mapaPersonajes = new HashMap<String, Personatge>();
        mapaObjetos = new HashMap<String, Objeto>();

    }

    static Mundo getIntanceMundo() {
        if (intanceMundo == null) {
            intanceMundo = new Mundo();
        }
        return intanceMundo;
    }

    /***********Funció Login + personatges + objectes actuals**********************************/
    public Jugador enter(String email, Login login){
    Jugador j = new Jugador();

        try{
        j.select("email",email);
        j.toString();
    }
        catch(Exception e)
    {
        e.printStackTrace();
        return null;
    }
        if(j.getContrasenya().equals(login.getPassword()))
    {
        j.seleccionarPersonajes(j);
        return j;
    }
        return null;
}

    /***********Funció crea nou jugador***********************/
    public Jugador newGamer(String email,Login login){
        Jugador j = new Jugador(login.getNom(),login.getPassword(),email);
        try {
            j.insert();
        } catch (Exception e) {
            return null;
        }
        return j;
    }

    /***********Funció que actualitza el personatge***********/
    public Personatge updatePersonatge(Personatge p) {
        try {
            boolean update = p.update();//true hi ha hagut modificacio, false no hi ha hagut modificació
            if (update == true) {
                return p;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMapaPartidaFormMinionGarcia(){
        Personatge p = new Personatge();
        try {
            p.select("id","45");
            return  this.newGame(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /***********Funció que retorna partida començada**********/
    public String saveMap(int idJugador) {
        DAOMapa mimapa = new DAOMapa(10, 10);//empty Map
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        if (mimapa.select(idJugador).mapEmpty()) {
            System.out.println("el mapa esta buit:" + mimapa.select(idJugador).mapEmpty());
            return null;
        } else {
            try {
                return mapper.writeValueAsString(mimapa.select(idJugador));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /***********Funció que crea partida a partir de nivell personatge**********/
    public String newGame(Personatge personatge) {
        Mapa mimapa = Mapa.miMapa(personatge);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        try {
            return mapper.writeValueAsString(mimapa);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    /************Funció que afegeix objecte a un personatge*****************/
    public String addObjectPersonatge(Objeto miob, int idp) {
        relacioPersonatgeObjecte relacio = new relacioPersonatgeObjecte(miob.getId(), idp);
        try {
            relacio.insert();
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "KO";
        }

    }

}









