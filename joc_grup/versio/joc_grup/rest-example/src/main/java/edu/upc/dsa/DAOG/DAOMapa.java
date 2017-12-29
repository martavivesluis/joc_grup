package edu.upc.dsa.DAOG;

import edu.upc.dsa.beans.Objeto;
import edu.upc.dsa.beans.mapa.Drawable;
import edu.upc.dsa.beans.mapa.Mapa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOMapa extends Mapa {

    public DAOMapa(int w, int h){
        super(w,h);
    }

    public Connection doGetConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        return conn;
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
            /*
              .PPPP...
              .PV.M...
              .PPPP...
              .P......
              .....H..
              ...V.... => Mapa .insert()
              ........
              ........
              /VVV

             (4,4) Vida 10
             (4,6) Enemic 15

             [
                                    "edu.upc.dsa.beans.mapa.EmptyCell",
                                    {}
                                ],
                                [
                                    "edu.upc.dsa.beans.mapa.EmptyCell",
                                    {}
                                ],
                                [
                                    "edu.upc.dsa.beans.mapa.EmptyCell",
                                    {}
             */
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
