package edu.upc.dsa;

import edu.upc.dsa.caracteres.Jugador;
import edu.upc.dsa.caracteres.Objeto;
import edu.upc.dsa.caracteres.Personatge;

import java.util.*;

public class Mundo {
    Map<String, Personatge> usuarios = new HashMap<String, Personatge>();//recibimos como clave el nombre del usuario,añade el usuario
    Map<String, Jugador> mapJugadors = new HashMap<String, Jugador>();//jugadors


    public void añadirObjetoAUsuario(Personatge u, Objeto o) {
        u.getMisObjetos().add(o);
    }
    public Boolean crearUsuario(Personatge u) {
        if (usuarios.containsKey(u.getNombre())) {
            return false;
        } else {
            usuarios.putIfAbsent(u.getNombre(),u);
            return true;
        }
    }
    public Personatge consultarUsuario(String nombre)
    {
        return usuarios.get(nombre);//si no esta devuelve un null
    }

    public boolean eliminarUsuario(String Nombre)
    {
        Personatge v = usuarios.remove(Nombre);
        if(v == null)
            return false;
        else
            return true;

    }/*
    public List<Objeto> consultarObjetosDeUsuario(Personatge u)
    {
        return u.MisObjetos;
    }
    public Objeto consultarObjetoDeUsuario(Personatge u, String nombreObjeto)
    {
        return u.getPrimerObjetoLlamado(nombreObjeto);
    }
    public void transferirObjetoEntreUsuarios(Personatge origen,Personatge destino, Objeto o)
    {
        origen.MisObjetos.remove(o);
        destino.MisObjetos.add(o);
    }
*/
    public Boolean crearJugador(Jugador jugador) {
       mapJugadors.put(jugador.nom, jugador);
       return true;
    }

    public void AfegirPersonatgeJugador(Jugador jugador, Personatge personatge)
    {
        jugador.getPersonatges().add(personatge);
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
    public Jugador getJugador(String nom) {
        return mapJugadors.get(nom);
    }
    public ArrayList<String> consultarPersonatgesJugador(Jugador u)
    {

        ArrayList<Personatge> totselsdaquestjugador = u.getPersonatges();
        ArrayList<String> totselsNomsdelspersonatgesdeljugador = new ArrayList<String>();
        for(Personatge p : totselsdaquestjugador){
            String nombre = p.getNombre();
            totselsNomsdelspersonatgesdeljugador.add(nombre);
        }
        return totselsNomsdelspersonatgesdeljugador;
    }




}
