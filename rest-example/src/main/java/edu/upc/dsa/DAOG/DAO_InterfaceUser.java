package edu.upc.dsa.DAOG;

import java.io.IOException;

public interface DAO_InterfaceUser {
    boolean loguejarUsuari(String contrassenya, String email) throws Exception;
    String recuperarContrassenya(String email);
}
