package edu.upc.dsa.beans;

public class Login {
    public Login(){
        nom ="";
        password="";
    }

    public Login(String pass){
        password = pass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    String nom;
}