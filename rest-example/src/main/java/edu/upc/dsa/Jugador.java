package edu.upc.dsa;

import java.util.ArrayList;

public class Jugador {
    public String nom;
    private String email;
    private String contrasenya;
    ArrayList <Usuario> personantgesJugador;

    public Jugador(String nom, String contrasenya, String email, Usuario personantge)
    {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.email = email;
        personantgesJugador= new ArrayList<Usuario>();

    }
    public Jugador()
    {
        this.nom = null;
        this.contrasenya = null;
        this.email = null;
        personantgesJugador= new ArrayList<Usuario>();
    }

    public String getNombre() {
        return nom;
    }
    public String getContrasenya() {
        return contrasenya;
    }
    public String getMail() {
        return email;
    }
    public ArrayList<Usuario> getPersonantgesJugador() {
        return personantgesJugador;
    }
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    public void setContrasenya(String contrasenya)
    {
        this.contrasenya = contrasenya;
    }

}
