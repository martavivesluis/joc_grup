package edu.upc.dsa.DAOG;

import edu.upc.dsa.beans.Jugador;

public class DAO_InterfaceUserImp extends DAO implements DAO_InterfaceUser {

    public boolean loguejarUsuari(String email, String contrassenya) throws Exception {
        boolean existeix = false;
        Jugador j = new Jugador();
        j.setEmail(email);//codifica contrassenya
        try{
            j.select("email",email);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if(j.getContrasenya().equals(contrassenya)) {
                System.out.println("usuari existent");
                return true;
        }
        return false;

    }
}




