package edu.upc.dsa.DAOG;

import edu.upc.dsa.beans.Jugador;
import edu.upc.dsa.beans.Objeto;
import edu.upc.dsa.beans.Personatge;

import java.sql.*;

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
    public Jugador seleccionarPersonajes(Jugador j){
        Connection conn = null;
        try {
            conn = doGetConnection();
            String sb ="SELECT idPersonatge FROM relaciopersonatgejugador WHERE " + "relaciopersonatgejugador.idjugador =" +this.getId();
            PreparedStatement pstm = conn.prepareStatement(sb);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id =""+rs.getObject(1);
                Personatge p = new Personatge();
                p.select("id",id);
                String sb2 ="SELECT idObjecte FROM relaciopersonatgeobjecte WHERE " + "relaciopersonatgeobjecte.idPersonatge ="+id;
                PreparedStatement pstm2 = conn.prepareStatement(sb2);
                ResultSet rss = pstm2.executeQuery();
                while (rss.next()) {
                    String id1 =""+rss.getObject(1);
                    Objeto nuevo = new Objeto();
                    nuevo.select("id",id1);
                    p.arrMisObjetos.add(nuevo);
                }
                j.personatges.add(p);

            }}
         catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return j;
    }
}




