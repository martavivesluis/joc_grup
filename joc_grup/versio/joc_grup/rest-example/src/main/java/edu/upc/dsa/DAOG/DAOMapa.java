package edu.upc.dsa.DAOG;

import edu.upc.dsa.beans.Monstruo;
import edu.upc.dsa.beans.Objeto;
import edu.upc.dsa.beans.Personatge;
import edu.upc.dsa.beans.mapa.Drawable;
import edu.upc.dsa.beans.mapa.EmptyCell;
import edu.upc.dsa.beans.mapa.Mapa;
import edu.upc.dsa.beans.mapa.ParedCell;

import java.sql.*;

public class DAOMapa extends Mapa {

    public DAOMapa(int w, int h){
        super(w,h);
    }
    public DAOMapa(){
        super();
    }
    public Connection doGetConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        return conn;
    }
    public  boolean mapEmpty(){
        for (int i = 0; i < this.doGetWidth(); i++) {
            for (int j = 0; j < this.doGetHeight(); j++) {
                if(!(this.doGetElement(i,j) instanceof EmptyCell))
                { return false;}else{ }}
        }
        return true;

    }
    public DAOMapa select(int idJugador) {
        DAOMapa mimapa = new DAOMapa(10,10);//MAPA DE EMPTYCELLS
        EmptyCell myEmptyCell = new EmptyCell();
        ParedCell myParedCell = new ParedCell();
        Connection conn = null;
        try {
            conn = doGetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < this.doGetWidth(); i++) {
            for (int j = 0; j < this.doGetHeight(); j++) {
                String insrt = "SELECT * FROM Mapa WHERE idJugador=" + idJugador + " AND columna = " + i + " AND fila= " + j;
                try {
                    PreparedStatement pstm = conn.prepareStatement(insrt);
                    ResultSet rs = pstm.executeQuery();
                    while (rs.next()) {

                        String idEl ="" + rs.getObject(4);
                        String type = "" + rs.getObject(5);//

                        if(type.equals("ParedCell"))
                        {   mimapa.putElement(i,j,myParedCell);}
                        if(type.equals("objeto")){
                            Objeto miobjeto = new Objeto();
                            miobjeto.select("id",idEl);//si existe nos devuelve objeto lleno
                            mimapa.putElement(i,j,miobjeto);
                        }
                        if(type.equals("monstruo")){
                            Monstruo mimontruo = new Monstruo();
                            mimontruo.select("id",idEl);
                            mimapa.putElement(i,j,mimontruo);
                        }
                        if(type.equals("personatge")){
                            Personatge mipersonatge = new Personatge();
                            mipersonatge.select("id",idEl);
                            mimapa.putElement(i,j,mipersonatge);
                        }
                        else if(type.equals("EmptyCell")){
                          mimapa.putElement(i,j,myEmptyCell);}
                        //per defecte emptycell
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }return mimapa;
    }
    public void insert(int idJugador){
        Connection conn = null;
        try {
            conn = doGetConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for(int i =0;i<this.doGetWidth();i++)
        {
            for(int j =0;j<this.doGetHeight();j++)
            {
                Drawable obj= this.doGetElement(i,j);
                int id = obj.dogetId();
                String tipus = obj.dogetTipus();
                 String insrt = "INSERT INTO Mapa (idJugador, columna, fila,idElemento, tipo) VALUES("+idJugador+","+i+","+j+","+id+",\""+tipus+"\" ) ON DUPLICATE KEY UPDATE"+
                " tipo=\""+tipus+"\",idElemento="+id+"";
                PreparedStatement pstm = null;
                try {
                    pstm = conn.prepareStatement(insrt);
                    pstm.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        }

    }
}