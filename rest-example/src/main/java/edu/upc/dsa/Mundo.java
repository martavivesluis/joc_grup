package edu.upc.dsa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mundo {
    Map<String, Usuario> usuarios = new HashMap<String, Usuario>();//recibimos como clave el nombre del usuario,añade el usuario
    Map<String, Jugador> mapJugadors = new HashMap<String, Jugador>();//jugadors


    public void añadirObjetoAUsuario(Usuario u, Objeto o) {
        //u.MisObjetos.add(o);
    }

    public Boolean crearUsuario(Usuario u) {
        if (usuarios.containsKey(u.getNombre())) {
            return false;
        } else {
            usuarios.putIfAbsent(u.getNombre(),u);
            u.insert();
            return true;
        }
    }
    public Usuario consultarUsuario(String nombre)
    {
        return usuarios.get(nombre);//si no esta devuelve un null
    }

    public boolean eliminarUsuario(String Nombre)
    {
        Usuario v = usuarios.remove(Nombre);
        if(v == null)
            return false;
        else
            return true;

    }/*
    public List<Objeto> consultarObjetosDeUsuario(Usuario u)
    {
        return u.MisObjetos;
    }
    public Objeto consultarObjetoDeUsuario(Usuario u, String nombreObjeto)
    {
        return u.getPrimerObjetoLlamado(nombreObjeto);
    }
    public void transferirObjetoEntreUsuarios(Usuario origen,Usuario destino, Objeto o)
    {
        origen.MisObjetos.remove(o);
        destino.MisObjetos.add(o);
    }
*/
    public Boolean crearJugador(Jugador jugador) {
       mapJugadors.put(jugador.nom, jugador);
       return true;
    }

    public void AfegirPersonatgeJugador(Jugador jugador, Usuario personatge)
    {
        jugador.personantgesJugador.add(personatge);
    }
    public List<Usuario> consultaPersonatgesJugador(Jugador jugador)
    {
      return jugador.personantgesJugador;
    }
    public String consultarNomJugador(String nombre)
    {
        Jugador j1= mapJugadors.get(nombre);
        String nom = j1.getNombre();
        return nom;
    }
    public String consultarContraJugador(String nombre)
    {
        Jugador j1= mapJugadors.get(nombre);
        String contrasenya = j1.getContrasenya();
        return contrasenya;

    }
}
