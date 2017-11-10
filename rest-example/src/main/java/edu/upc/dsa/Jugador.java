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
}
