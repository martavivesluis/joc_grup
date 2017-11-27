package edu.upc.dsa.DAOG;

import edu.upc.dsa.Personatge;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAO {
    //dao
    public int id = 0;//totes les taules tindran un identificador del tipos enter
    protected boolean idAutogen = true;
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        return conn;
    }
    private Method findSetMethod(String field) {
        String s = "set"+field.substring(0,1).toUpperCase()+field.substring(1);

        System.out.println("setter "+s);

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method m: methods) {
            System.out.println(m.getName());
            if (m.getName().equals(s)) {
                System.out.println("metode trobat" + m.getName());
                return m;
            }
        }

        return null;
    }
    private void addField(String field, Object value) throws  Exception {
        System.out.println("field: "+field+" "+value);

         Method m = findSetMethod(field); // email --> setEmail, setId, setName
         Object[] args = {value};
         m.invoke(this, args);
    }
    private void addRow(ResultSet rs) throws  Exception{
        ResultSetMetaData rsmd = rs.getMetaData();
        int totalColumnes = rsmd.getColumnCount();

        for (int i=0; i< totalColumnes; i++) {
            addField(rsmd.getColumnName(i+1), rs.getObject(i+1));
        }
    }
    private String queryInsert() {
        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(this.getClass().getSimpleName());//NOM DE LA CLASSE USUARIOS...
        System.out.println(sb.toString());//substituir por log4java
        sb.append("(");

        ArrayList<Field> almisatributos = new ArrayList<Field>();
        almisatributos.addAll(Arrays.asList(this.getClass().getFields()));
        Field[] misatributos = new Field[almisatributos.size()];
        misatributos = almisatributos.toArray(misatributos);

        this.getClass().getDeclaredFields();
        Field atributo;
        int j = 0;
        int identificadortaula = 0;
        int totalAtributs = 0;
        StringBuffer values = new StringBuffer();

        try {
            for (int i = 0; i < misatributos.length; i++) {
                atributo = misatributos[i];
                System.out.println(atributo.getName().toString());

                System.out.println(atributo.getGenericType().toString());

                if(atributo.getName().toString().equals("id") && !idAutogen)
                {
                    values.append(""+atributo.get(this) + ",");
                    sb.append(atributo.getName().toString() + ",");
                } else  if (atributo.getGenericType().toString().equals("int"))
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
        sb.setLength(sb.length() - 1);
        values.setLength(values.length() - 1);
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
    public void insert() throws Exception{
        String theQuery = this.queryInsert();
        System.out.println(theQuery);

        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        PreparedStatement pstm = conn.prepareStatement(theQuery);
        pstm.execute();

        if(idAutogen) {
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.setId((int) generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        // releaseConnectoin
    }
    public void select() throws Exception {
        String theQuery = this.querySelect();
        select(theQuery);

    }


    public void select(String key, String value) throws Exception {
        String theQuery="";// SELECT * FROM t WHERE email = ' yo@hola.com0
        select(theQuery);

    }

    public void select(String theQuery) throws Exception{


        Connection con = getConnection();
        PreparedStatement pstm = con.prepareStatement(theQuery);
        pstm.setInt(1, this.getId());
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            addRow(rs);
        }


    }

    public String selectAllQuery(Class myclass) {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(myclass.getSimpleName());
        return sb.toString();
    }
    private String querySelect() {
        StringBuffer sb = new StringBuffer("SELECT * FROM "+this.getClass().getSimpleName()).append(" WHERE id=?");
        return sb.toString();
    }
    public boolean update()throws Exception {
        Connection con = getConnection();
        StringBuffer consulta = new StringBuffer("UPDATE "+this.getClass().getSimpleName()+" SET ");
        Field[] atributos = this.getClass().getDeclaredFields();
        for(int i = 0;i<atributos.length;i++)//ignorem vectors array
        {
            if(atributos[i].getGenericType().toString().equals("class java.lang.String"))
            {
                consulta.append(atributos[i].getName() + "=?,"); //--> nombre =?,
            }
            else if(atributos[i].getGenericType().toString().equals("int")) {
                consulta.append(atributos[i].getName() + "=?,"); //--> nombre =?,
            }
            else{}

        }
        consulta.setLength(consulta.length()-1);
        consulta.append(" WHERE id='"+this.getId()+"'");//
        System.out.println(consulta);
        PreparedStatement pstm = con.prepareStatement(consulta.toString());//substituim els interrogants
        System.out.println(pstm.toString());
        try{
            addFieldsToQueryUpdate(pstm);
            return true;
        }
        catch(Exception e){
            return false;

    }}//updae jugador set(nom=?,...)where id =get.id
    public String queryDeleteTable(){

        StringBuffer sb = new StringBuffer("DELETE * FROM "+this.getClass().getSimpleName()).append(" WHERE id=?");
        return sb.toString();

}
    public void addFieldsToQueryUpdate(PreparedStatement pstm)throws Exception
    {
        int j =0;
        Field[] fields = this.getClass().getDeclaredFields();
        for(int i = 1;i<fields.length;i++)
        try
        {
            Method method = findGetMethod(fields[j].getName());
            System.out.println("mimetodo:"+method.getName().toString());
            Object object = method.invoke(this);
            System.out.println("valor:"+object.toString());
            pstm.setObject(i,object.toString());
            j++;


        }

        catch(Exception e){}
        System.out.println("updatestatement:"+pstm.toString());
        pstm.executeUpdate();
    }









    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
//sustituir els interrogants de les consultes

    private Method findGetMethod(String field) {
        String s = "get"+field.substring(0,1).toUpperCase()+field.substring(1);

        System.out.println("getter "+s);

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method m: methods)
        {
            System.out.println(m.getName());
            if (m.getName().equals(s)) {
                System.out.println("metode trobat" + m.getName());
                return m;
            }
        }
        return null;
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
       // Personatge t = new Personatge("Anna", 1, 2, 3, 40);

        //t.insert();
        //t.delete();

    }
}
