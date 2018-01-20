package edu.upc.dsa.DAOG;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.upc.dsa.beans.Personatge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAO_PersonajeImpl extends DAO implements DAO_Personaje {
    public void rankingPersonatges() {
    }
    public void subirNivelBaseDeDatos(int id, int nivel){
        int i = 0;
        Connection conn = null;
        try {
            conn = doGetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String insrt = "UPDATE Personatge SET nivel ="+nivel+" WHERE id="+id;
        PreparedStatement pstm = null;
        try {
            pstm = conn.prepareStatement(insrt);
            pstm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public String actualitzarPersonatge(Personatge p){
        String response ="OK";
        int i = 0;
        Connection conn = null;
        try {
            conn = doGetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        while (i < p.getArrMisObjetos().size()) {
            relacioPersonatgeObjecte myRelation = new relacioPersonatgeObjecte(p.getArrMisObjetos().get(i).getId(), p.getId());
            try {
                myRelation.insert();
                i++;

            }  catch (MySQLIntegrityConstraintViolationException e) {
                i++;

            } catch (Exception e) {
                response ="KO";
            }
        }

        return response;
        }

    }


