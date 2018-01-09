package edu.upc.dsa.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upc.dsa.DAOG.DAOMapa;
import edu.upc.dsa.DAOG.RelacioPersonatgeJugador;
import edu.upc.dsa.DAOG.relacioPersonatgeObjecte;
import edu.upc.dsa.beans.*;
import edu.upc.dsa.beans.mapa.Mapa;

import javax.ws.rs.core.Response;
import java.util.HashMap;
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
        j.selectPersonatgesFromDb(j);
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
    /***********Funció crea nou personatge***********************/
    public Personatge createNewPersonatge(String nompersonatge, String type, String id){
        Answer respuesta = new Answer();
        System.out.println(nompersonatge);
        int tipus = Integer.parseInt(type);
        Jugador j = new Jugador();
        Personatge p;
        try {
            j.select("id",id);
            if(j.getNom()==null){
                respuesta.setResposta("KO");
                return null;// nocontent,usuari inexistent
            }
            if(j.selectNumberOfPersonatges(j.getId())>3)
            {
                respuesta.setResposta("MAX");
                p = new Personatge();
                p.setId(-1);
                return p;//no autoritzat massas jugadors
            }
            else{
                p = new Personatge(tipus,nompersonatge);
                p.insert();
                RelacioPersonatgeJugador r = new RelacioPersonatgeJugador(Integer.parseInt(id),p.getId());
                r.insert();
                respuesta.setResposta("OK");//
                return p;

            }
        } catch (Exception e) {
            e.printStackTrace();
            respuesta.setResposta("KO");//
            return null;
        }
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

    /***********Funció que retorna partida començada**********/
    public String loadMapFromDbAndStringifyIt(int idJugador) {
         DAOMapa mimapa = new DAOMapa(10, 10);//empty Map
         mimapa = mimapa.select(idJugador);
        if (mimapa.doIsMapEmpty()) {
            System.out.println("el mapa esta buit!");
            return null;
        }
        try {ObjectMapper mapper = new ObjectMapper();
            mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            return mapper.writeValueAsString(mimapa);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /***********Funció que crea partida a partir de nivell personatge**********/
    public String newGame(Personatge personatge,int idJugador) {
        DAOMapa mimapa = Mapa.miMapa(personatge);
        ObjectMapper mapper = new ObjectMapper();
        mimapa.upsert(idJugador);
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









