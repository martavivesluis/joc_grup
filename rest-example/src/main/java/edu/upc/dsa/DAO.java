package edu.upc.dsa;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.lang.Object;
import java.lang.reflect.AccessibleObject;
import java.lang.TypeNotPresentException;

public class DAO {

    private String queryInsert() {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(this.getClass().getSimpleName());//NOM DE LA CLASSE USUARIOS...
        System.out.println(sb.toString());//substituir por log4java
        sb.append("(");
        Field[] misatributos = this.getClass().getDeclaredFields();
        Field atributo;
        int j = 0;
        int totalAtributs = 0;
        StringBuffer values = new StringBuffer();
        try {
            for (int i = 0; i + 1 < misatributos.length; i++) {
                atributo = misatributos[i];
                System.out.println(atributo.getName().toString());
                sb.append(atributo.getName().toString() + ",");
                values.append(atributo.get(this) + ",");
                j = i;
            }
            values.append(misatributos[j + 1].get(this) + "");
        } catch (Exception e) {

        }
        atributo = misatributos[misatributos.length - 1];
        System.out.println(atributo.getName().toString());
        sb.append(atributo.getName().toString());
        sb.append(")VALUES(" + values.toString() + ")");
        System.out.println(sb);
        return sb.toString();
    }

    public void insert() {
        String theQuery = this.queryInsert();
        try {
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jugadores?" + "user=myapp&password=12345&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO usuario(nombre,password,nivel,ataque,defensa,resistencia)VALUES('IMsdsaf','HHsfggf',1,2,3,4)");
            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            ;
        }

//        rs.next(); rs.getString(1)

        // releaseConnectoin
    }

    public static void main(String[] args) {
        Usuario t = new Usuario("IMsdsaf", "HHsfggf", 1, 2, 3, 4);
        t.insert();

    }
}
