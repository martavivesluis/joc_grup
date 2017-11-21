package edu.upc.dsa.DAOG;

import edu.upc.dsa.Jugador;
import edu.upc.dsa.Personatge;

import java.lang.reflect.Field;
import java.sql.*;
import java.lang.Object;
import java.lang.reflect.AccessibleObject;
import java.lang.TypeNotPresentException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public int id = 0;//totes les taules tindran un identificador del tipos enter
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        System.out.println("Connected to database");
        return con;
    }

    public String selectAllQuery(Class seleccionada) {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(seleccionada.getSimpleName());
        return sb.toString();
    }
    public List selectAll(Class seleccionada) throws SQLException,ClassNotFoundException
    {
        List<Object> objects = new ArrayList<>();//en el nostre cas usuaris
        Connection mycon = getConnection();
        String query = selectAllQuery(seleccionada);//construim sentencia
        PreparedStatement pstm = mycon.prepareStatement(query);
        ResultSet result = pstm.executeQuery();
        while(result.next())
        {
            Object nou = new Object();
            objects.add(nou);
        }

        return objects;

    }
    public List<Jugador> selectAllJugadors() throws SQLException,ClassNotFoundException
    {
        return selectAll(Jugador.class);
    }

    public boolean userLogueado(String nombre,String contraseña)
    {

        return true;
    }







    private String queryInsert() {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(this.getClass().getSimpleName());//NOM DE LA CLASSE USUARIOS...
        System.out.println(sb.toString());//substituir por log4java
        sb.append("(");
        Field[] misatributos = this.getClass().getDeclaredFields();
        Field atributo;
        int j = 0;
        int identificadortaula = 0;
        int totalAtributs = 0;
        StringBuffer values = new StringBuffer();
        try {
            for (int i = 0; i + 1 < misatributos.length; i++) {
                atributo = misatributos[i];
                System.out.println(atributo.getName().toString());
                if(atributo.getName().toString().equals("id"))
                {
                   String identificador = (atributo.get(this)+"");
                    Integer.parseInt(identificador);
                }
                System.out.println(atributo.getGenericType().toString());

                if (atributo.getGenericType().toString().equals("int"))
                {
                values.append(atributo.get(this) + ",");
                    sb.append(atributo.getName().toString() + ",");
                }
                else if (atributo.getGenericType().toString().equals("class java.lang.String")){
                    values.append("'"+atributo.get(this) + "',");
                    sb.append(atributo.getName().toString() + ",");

                }
                else
                {
                    //caso del array de objetos, creeemos tabla a partir del identificador

                }
                j = i;
            }
            values.append(misatributos[j + 1].get(this) + "");
        } catch (Exception e) {

        }
        atributo = misatributos[misatributos.length - 1];
        System.out.println(atributo.getName().toString());
        sb.append(atributo.getName().toString());
        sb.append(")VALUES(" + values.toString() + ")");
        System.out.println(sb.toString());
        return sb.toString();//consulta a realitzar
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void insert() {
        String theQuery = this.queryInsert();
        System.out.println(theQuery);
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
            PreparedStatement pstm = conn.prepareStatement(theQuery);
            pstm.execute();
            this.setId(1);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                System.out.println("regitre duplicat");
            }
        }

//        rs.next(); rs.getString(1)

        // releaseConnectoin
    }
    public String queryDelete(){
        StringBuffer sb = new StringBuffer("DELETE * FROM  ");
        sb.append(this.getClass().getSimpleName());//NOM DE LA CLASSE USUARIOS...
        System.out.println(sb.toString());//substituir por log4java
        sb.append(" WHERE ");
        Field[] misatributos = this.getClass().getDeclaredFields();
        Field atributo;
        int j = 0;
        int totalAtributs = 0;


        StringBuffer values = new StringBuffer();
        try {
            for (int i = 0; i + 1 < misatributos.length; i++) {
                atributo = misatributos[i];
                System.out.println(atributo.getName().toString());
                System.out.println(atributo.getGenericType().toString());
                sb.append(atributo.getName().toString() + "=");

                if (atributo.getGenericType().toString().equals("int"))
                {
                    sb.append(atributo.get(this).toString() + " AND ");}

                else if(atributo.getGenericType().toString().equals("string")) {
                    sb.append("'"+atributo.get(this).toString() + "' AND ");
                }
                j = i;
            }

            atributo = misatributos[misatributos.length - 1];
            sb.append(atributo.getName().toString()+"=");
            sb.append(atributo.get(this).toString());
        } catch (Exception e) {

        }

        System.out.println(sb.toString());
        return sb.toString();//consulta a realit

    }
public void delete() {
    String theQuery = this.queryDelete();
    System.out.println(theQuery);
    try {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        PreparedStatement pstm = conn.prepareStatement(theQuery);
        pstm.execute();
        System.out.println("regitre esborrat");

    } catch (SQLException e) {
        if (e.getErrorCode() == 1062) {
            System.out.println("regitre duplicat");
        }
    }
}
    public static void main(String[] args) {
        Personatge t = new Personatge("Anna", 1, 2, 3, 40);
        t.insert();
        //t.delete();

    }
}
