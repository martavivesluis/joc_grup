package edu.upc.dsa;

public class Jugador {
    private String nom;
    private String email;
    private String contrasenya;
    private Usuario personantge;

    public Jugador(String nom, String contrasenya, String email, Usuario personantge)
    {
        this.nom = nom;
        this.contrasenya = contrasenya;
        this.email = email;
        this.personantge = personantge;

    }
    public Jugador()
    {
        this.nom = null;
        this.contrasenya = null;
        this.email = null;
        this.personantge = null;
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
    public Usuario getPersonantge() {
        return personantge;
    }
    public void setNom(String nom)
    {
        this.nom = nom;
    }
    public void setContrasenya(String contrasenya)
    {
        this.contrasenya = contrasenya;
    }
    public void setUsuario (Usuario personantge)
    {
        this.personantge = personantge;
    }
}
