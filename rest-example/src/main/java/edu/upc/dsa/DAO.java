package edu.upc.dsa;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.lang.Object;
import java.lang.reflect.AccessibleObject;
import java.lang.TypeNotPresentException;
import java.sql.SQLException;

public class DAO {

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
                sb.append(atributo.getName().toString() + ",");

                if (atributo.getGenericType().toString().equals("int"))
                {
                values.append(atributo.get(this) + ",");}
                else if (atributo.getGenericType().toString().equals("class java.lang.String")){
                    values.append("'"+atributo.get(this) + "',");
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

    public void insert() {
        String theQuery = this.queryInsert();
        System.out.println(theQuery);
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
            PreparedStatement pstm = conn.prepareStatement(theQuery);
            pstm.execute();


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
        Usuario t = new Usuario("Anna", "1234", 1, 2, 3, 40);
        t.insert();
        //t.delete();

    }
}
