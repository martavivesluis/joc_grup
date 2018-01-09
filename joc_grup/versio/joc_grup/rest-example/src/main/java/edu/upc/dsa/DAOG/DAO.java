package edu.upc.dsa.DAOG;

import edu.upc.dsa.beans.Partida;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DAO {
    //comandes aplicables totes les classes
    public int id = -1;//totes les taules tindran un identificador del tipos enter
    protected boolean idAutogen = true;
    protected boolean hasId = true;

    public DAO() {

    }

    public Connection doGetConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        return conn;
    }
    public boolean isIdAutogen() {
        return idAutogen;
    }
    public void setIdAutogen(boolean idAutogen) {
        this.idAutogen = idAutogen;
    }
    private Method findSetMethod(String field) {
        String s = "set"+field.substring(0,1).toUpperCase()+field.substring(1);
        Method[] methods = this.getClass().getMethods();
        for (Method m: methods) {
            if (m.getName().equals(s)) {
                return m;
            }
        }

        return null;
    }
    public boolean existent(boolean existent){
       return existent;
    }
    private void addField(String field, Object value) throws  Exception {
        if(!hasId && field.equals("id")){
            return;
        }
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
    private void selectInsertLmited(String query){

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

                if( atributo.getName().toString().equals("id")){
                    if( !idAutogen ){
                        values.append(""+atributo.get(this) + ",");
                        sb.append(atributo.getName().toString() + ",");
                    }
                } else  if (atributo.getGenericType().toString().equals("int"))
                {
                    values.append(atributo.get(this) + ",");
                    sb.append(atributo.getName().toString() + ",");
                }
                else if (atributo.getGenericType().toString().equals("class java.lang.String")){
                    values.append("'"+atributo.get(this) + "',");
                    sb.append(atributo.getName().toString() + ",");

                }
                else if(atributo.getGenericType().toString().equals("class java.util.Date"))
                {values.append("'"+atributo.get(this) + "',");
                    sb.append(atributo.getName().toString() + ",");}
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

        if(idAutogen && hasId) {
            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.setId((int) generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }

        // releaseConnectoin
    }
    public boolean select2(String Query, Object value) throws Exception {
        Connection con = doGetConnection();
        boolean ret = false;
        PreparedStatement pstm = con.prepareStatement(Query);
        pstm.setObject(1, value);
        ResultSet rs = pstm.executeQuery();
        while (rs.next()) {
            ret = true;
            addRow(rs);
        }
        return ret;
    }
    public boolean select()throws Exception{
        if(!hasId){
            throw new Exception();
        }
        String query = this.querySelect("id");
       return  select2(query, this.getId());
    }
    public boolean select(String key,String value) throws Exception{
        String query = this.querySelect(key);
        return select2(query, (Object)value);
    }
    public String selectAllQuery() {

        StringBuffer sb = new StringBuffer("SELECT * FROM ");
        sb.append(this.getClass().getSimpleName());
        return sb.toString();
    }
    private String querySelect(String key) {
        StringBuffer sb = new StringBuffer("SELECT * FROM "+this.getClass().getSimpleName()).append(" WHERE "+key+"=?");
        System.out.println(sb.toString());
        return sb.toString();
    }
    public boolean update()throws Exception {
        Connection con = doGetConnection();
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
            int modificar = addFieldsToQueryUpdate(pstm);
            if(modificar == 1){return true;}
            else return false;
        }
        catch(Exception e){
            return false;

    }}//updae jugador set(nom=?,...)where id =get.id
    public String queryDeleteTable(){

        StringBuffer sb = new StringBuffer("DELETE * FROM "+this.getClass().getSimpleName()).append(" WHERE id=?");
        return sb.toString();

}
    public int addFieldsToQueryUpdate(PreparedStatement pstm)throws Exception {
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
        return pstm.executeUpdate();// 0 cap modificacio 0.txt ninguna

    }
    public static List<Field> doGetAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            doGetAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
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
        StringBuffer sb = new StringBuffer("DELETE FROM  ");
        sb.append(this.getClass().getSimpleName());//NOM DE LA CLASSE USUARIOS...
        System.out.println(sb.toString());//substituir por log4java
        sb.append(" WHERE ");
        Field[] misatributos = this.getClass().getDeclaredFields();
        Field atributo;
        int j = 0;
        int totalAtributs = 0;
        StringBuffer values = new StringBuffer();
        try {
            for (int i = 0; i + 1 < misatributos.length; i++)
            {
                atributo = misatributos[i];
                System.out.println(atributo.getName().toString());
                System.out.println(atributo.getGenericType().toString());
                sb.append(atributo.getName().toString() + "=");
                if (atributo.getGenericType().toString().equals("int"))
                {
                    sb.append(atributo.get(this).toString() + " AND");}

                else if(atributo.getGenericType().toString().equals("class java.lang.String")) {
                    sb.append("'"+atributo.get(this).toString() + "' AND ");
                }
                else{}
                j = i;
            }
        sb.setLength(sb.length()-4);//borrem el and del final
            atributo = misatributos[misatributos.length -20];
            //sb.append(atributo.getName().toString()+"=");
            //sb.append(atributo.get(this).toString());
        } catch (Exception e) {

        }

        System.out.println(sb.toString());
        return sb.toString();//consulta a realitzar

    }
    public ArrayList<Partida> ranking(){
        ArrayList<Partida> milista = new ArrayList<Partida>();
        Connection con = null;
        try {
            con = doGetConnection();
            String sb ="SELECT * FROM Partida ORDER BY puntos DESC LIMIT 5";
            PreparedStatement pstm = con.prepareStatement(sb);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String idJugador =""+rs.getObject(1);//IDJUGADOR
                String puntos =""+rs.getObject(2);
                String inici =""+rs.getObject(3);
                String idPartida =""+rs.getObject(4);
                Partida p = new Partida(Integer.parseInt(idJugador),Integer.parseInt(puntos),inici);
                p.setId(Integer.parseInt(idPartida));
                milista.add(p);
                System.out.println(idJugador+" "+puntos+" "+inici+" "+idPartida);}
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return milista;
    }
    public boolean delete() {
    String theQuery = this.queryDelete();
    System.out.println(theQuery);
    try {
        Connection conn = null;
        conn = DriverManager.getConnection("jdbc:mysql://localhost/juego?" + "user=myapp&password=1234&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC");
        PreparedStatement pstm = conn.prepareStatement(theQuery);
        int update = pstm.executeUpdate();
        if(update==1){return true;}
        System.out.println(update);//0 no fa res,0.txt ha esborrat
        System.out.println("regitre esborrat");
        return false;// el registre enviat no existeix

    } catch (SQLException e) {
        if (e.getErrorCode() == 1062) {
            System.out.println("regitre duplicat");
            return false;
        }
        return false;
    }
}
       public static void main(String[] args) {


        //t.insert();
        //t.delete();

    }

    public boolean isHasId() {
        return hasId;
    }

    public void setHasId(boolean hasId) {
        this.hasId = hasId;
    }
}
