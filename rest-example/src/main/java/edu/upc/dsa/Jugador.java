package edu.upc.dsa;

import java.util.ArrayList;

public class Jugador {
    public String nom;
    private String email;
    private String contrasenya;

    public void setPersonatges(ArrayList<Personatge> personatges) {
        this.personatges = personatges;
    }

    ArrayList <Personatge> personatges; //etiqueta buida

    //constructor d'un jugador
    public Jugador(String nom, String contrasenya, String email)
    {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.email = email;
        personatges = new ArrayList<Personatge>();

    }
    public Jugador()
    {
        this.nom = null;
        this.contrasenya = null;
        this.email = null;
        personatges = new ArrayList<Personatge>();
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

    public ArrayList<Personatge> getPersonatges() {
        return personatges;
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
