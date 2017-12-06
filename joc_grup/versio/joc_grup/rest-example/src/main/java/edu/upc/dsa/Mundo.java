package edu.upc.dsa;

import edu.upc.dsa.mapa.Mapa;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo {
    Map<String, Personatge> mapaPersonajes;
    Map<Integer, Jugador> mapaJugadores;
    Map<String, Objeto> mapaObjetos;
    Mapa mapa = new Mapa(5,5);


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
        Objeto objeto1 = new Objeto("pico", "herramienta", "epico", 100, 1);
        Objeto objeto2 = new Objeto("revolver", "pistola", "raro", 99, 1);
        nodet.getArrMisObjetos().add(objeto1);
        vidente.getArrMisObjetos().add(objeto2);
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
        mapaObjetos.put("pico", objeto1);
        mapaObjetos.put("revolver", objeto2);


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


    /*public void añadirObjetoPersonaje(Personatge u, Objeto o) {
        mapaPersonajes.get(u.getNombre()).arrMisObjetos.add(o);
    }
    public Boolean crearUsuario(Jugador j) {
        if (mapaJugadores.containsKey(j.getId())){
            return false;
        } else {
            mapaJugadores.putIfAbsent(j.getId(),j);
            return true;
        }
    }
    public void perderRes(Personatge u){
        u.setResistencia(u.getResistencia()-1);
    }
    public boolean eliminarUsuario(int id){
        Jugador v = mapaJugadores.remove(id);
        if(v == null)
            return false;
        else
            return true;
    }
    public List<Objeto> consultarObjetosDeUsuario(Personatge u){
        return u.arrMisObjetos;
    }
    public Objeto consultarObjetoDeUsuario(Personatge u, String nombreObjeto){
        return null;//.getPrimerObjetoLlamado(nombreObjeto);
    }
    public void transferirObjetoEntreUsuarios(Personatge origen,Personatge destino, Objeto o) {
        origen.arrMisObjetos.remove(o);
        destino.arrMisObjetos.add(o);
    }
    public boolean afegirPersonatgeJugador(Jugador jugador, Personatge p)// añadir personaje a jugador
    {
        Personatge j = mapaPersonajes.put(p.getNombre(),p);
        if(j!=null)
            return false;
        mapa.putElement(0,0,p);//afegim personatge al mapa
        jugador.personatges.add(p);
        return true;
    }
    public String consultarNomJugador(int id) {
        Jugador j1 =mapaJugadores.get(id);
        return j1.getNombre();
    }
    public ArrayList<String> consultarPersonatgesJugador(Jugador u) {
        ArrayList<Personatge> totselsdaquestjugador = u.getPersonatges();
        ArrayList<String> totselsNomsdelspersonatgesdeljugador = new ArrayList<String>();
        for(Personatge p : totselsdaquestjugador){
            String nombre = p.getNombre();
            totselsNomsdelspersonatgesdeljugador.add(nombre);
        }
        return totselsNomsdelspersonatgesdeljugador;
    }
    public Jugador consultarUsuarioMail(String mail) {
        int id = mail.hashCode();
        return consultarUsuario(id);
    }
    public Jugador consultarUsuario(int id) {
        return mapaJugadores.get(id);//si no esta devuelve un null
    }*/