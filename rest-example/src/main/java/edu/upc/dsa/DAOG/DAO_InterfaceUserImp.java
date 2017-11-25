package edu.upc.dsa.DAOG;

import edu.upc.dsa.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO_InterfaceUserImp extends DAO implements DAO_InterfaceUser{

    public boolean loguejarUsuari(String nombre,String contrassenya,String email){
    Jugador j = new Jugador(nombre,contrassenya,email);




        return true;
    }

    public boolean actualitzarDades(int id,String contrassenya)throws Exception{//apartir d'un usuari loguejat
    StringBuffer sb = new StringBuffer("UPDATE jugador SET contrasenya ='"+contrassenya+"'WHERE (id="+id+")");
    String consulta = sb.toString();
    try{
        Connection con = getConnection();
        System.out.println("connectat");
        PreparedStatement pstm = con.prepareStatement(consulta);
        ResultSet rs = pstm.executeQuery();

    }
    catch(Exception e){
        return false;
        }





        return true;
    }//apartir del mail--> id
    public String recuperarContrassenya(String email){
        return null;
    }
}
