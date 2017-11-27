package edu.upc.dsa;

import edu.upc.dsa.DAOG.DAO_InterfaceUserImp;

import java.util.ArrayList;

public class Jugador extends DAO_InterfaceUserImp {
    public String nom;
    public String email;
    public String contrasenya;


    public void setPersonatges(ArrayList<Personatge> personatges) {
        this.personatges = personatges;
    }

    ArrayList<Personatge> personatges; //etiqueta buida

    //constructor d'un jugador
    public Jugador(String nom, String contrasenya, String email) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.email = email;
        personatges = new ArrayList<Personatge>();
        idAutogen = false;
        id = email.hashCode();
    }

    public Jugador() {
        this.nom = null;
        this.contrasenya = null;
        this.email = null;
        personatges = new ArrayList<Personatge>();
        idAutogen = false;
        id = 0;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        id = email.hashCode();
    }

    public String getNom() {

        return nom;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.id).append(" ").append(this.email).append(" ").append(contrasenya).append(" ").append(nom);

        return sb.toString();
    }





}
