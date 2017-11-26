package edu.upc.dsa.DAOG;

public interface DAO_InterfaceUser {
    boolean loguejarUsuari(String contrassenya, String email)throws Exception;
    String recuperarContrassenya(String email);
    }
