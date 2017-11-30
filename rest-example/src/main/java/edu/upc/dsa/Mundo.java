package edu.upc.dsa;

import edu.upc.dsa.mapa.Mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo {
    Map<String, Personatge> personajes = new HashMap<String, Personatge>();//recibimos como clave el nombre del usuario,añade el usuario
    Map<Integer, Jugador> jugadores = new HashMap<Integer, Jugador>();//jugadors
    Mapa mapa = new Mapa(5,5);

    public void añadirObjetoPersonaje(Personatge u, Objeto o) {
        personajes.get(u.getNombre()).arrMisObjetos.add(o);
    }
    public Boolean crearUsuario(Jugador j) {
        if (jugadores.containsKey(j.getId())){
            return false;
        } else {
            jugadores.putIfAbsent(j.getId(),j);
            return true;
        }

    }
    public void perderRes(Personatge u){
        u.setResistencia(u.getResistencia()-1);
    }

    public boolean eliminarUsuario(int id){
        Jugador v = jugadores.remove(id);
        if(v == null)
            return false;
        else
            return true;

    }
    public List<Objeto> consultarObjetosDeUsuario(Personatge u){
        return u.arrMisObjetos;
    }
    public Objeto consultarObjetoDeUsuario(Personatge u, String nombreObjeto){
        return u.getPrimerObjetoLlamado(nombreObjeto);
    }
    public void transferirObjetoEntreUsuarios(Personatge origen,Personatge destino, Objeto o) {
        origen.arrMisObjetos.remove(o);
        destino.arrMisObjetos.add(o);
    }

    public boolean afegirPersonatgeJugador(Jugador jugador, Personatge p)
    {
        Personatge j = personajes.put(p.getNombre(),p);
        if(j!=null)
            return false;
        mapa.putElement(0,0,p);
        jugador.personatges.add(p);
        return true;
    }
    public String consultarNomJugador(int id) {
        Jugador j1 =jugadores.get(id);
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
        return jugadores.get(id);//si no esta devuelve un null
    }
}
