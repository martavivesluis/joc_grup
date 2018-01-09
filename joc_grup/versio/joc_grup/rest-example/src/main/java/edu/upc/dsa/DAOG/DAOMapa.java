package edu.upc.dsa.DAOG;

import edu.upc.dsa.beans.Jugador;
import edu.upc.dsa.beans.Monstruo;
import edu.upc.dsa.beans.Objeto;
import edu.upc.dsa.beans.Personatge;
import edu.upc.dsa.beans.mapa.*;

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
    public  boolean doIsMapEmpty(){
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
        AiguaCell myAiguaCell = new AiguaCell();
        PedraCell myPedraCell = new PedraCell();
        FocCell myFocCell = new FocCell();
        PortaCell myPortaCell= new PortaCell();

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

                        //Cel·les
                        if(type.equals("ParedCell"))
                        {   mimapa.putElement(i,j,myParedCell);}
                        if(type.equals("AiguaCell"))
                        {   mimapa.putElement(i,j,myAiguaCell);}
                        if(type.equals("FocCell"))
                        {   mimapa.putElement(i,j,myFocCell);}
                        if(type.equals("PedraCell"))
                        {   mimapa.putElement(i,j,myPedraCell);}
                        if(type.equals("PortaCell"))
                        {   mimapa.putElement(i,j,myPortaCell);}

                        //Objectes
                        if(type.equals("Objeto")){
                            Objeto miobjeto = new Objeto();
                            miobjeto.select("id",idEl);//si existe nos devuelve objeto lleno
                            mimapa.putElement(i,j,miobjeto);
                        }

                        //monstruo
                        if(type.equals("Monstruo")){
                            Monstruo mimontruo = new Monstruo();
                            mimontruo.select("id",idEl);
                            mimapa.putElement(i,j,mimontruo);
                        }
                        //personatge
                        if(type.equals("Personatge")){
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
    public void upsert(int idJugador){
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
                Drawable cella= this.doGetElement(i,j);
                int id = 0;
                String tipus;
                if(cella instanceof DAO){
                    id = ((DAO)cella).getId();
                }
                tipus=(cella).getClass().getSimpleName();

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
