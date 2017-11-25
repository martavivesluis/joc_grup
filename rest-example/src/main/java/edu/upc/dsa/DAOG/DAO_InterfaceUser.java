package edu.upc.dsa.DAOG;

public interface DAO_InterfaceUser {
    boolean loguejarUsuari(String nombre, String contrassenya, String email);
    boolean actualitzarDades(int id, String contrassenya)throws Exception;//apartir del mail--> id
    String recuperarContrassenya(String email);
    }
