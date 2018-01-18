package edu.upc.dsa.beans;

import edu.upc.dsa.DAOG.DAO;
import edu.upc.dsa.DAOG.DAO_InterfaceUserImp;
import edu.upc.dsa.beans.Jugador;
import edu.upc.dsa.beans.Personatge;
import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class JugadorTest extends TestCase {
DAO midao;
    DAO_InterfaceUserImp imp;

    public void testIdEsDiferent0()
    {
        try {
            Jugador jugador = new Jugador("Joan","1234","s@hola.com");
            Jugador j = new Jugador("narta","1234","martavivesluis@gmail.com");
            j.insert();
            assertThat(jugador.getId(), is(not(0)));
            System.out.println("p1"+ jugador.getId());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
public void test3usuaris()
{ Jugador j = new Jugador("laura","1234","laura@gmail.com");

    try {
        j.insert();
        j.setContrasenya("256612");
        j.update();
    } catch (Exception e) {
        e.printStackTrace();
    }}
public void testDelete()
{
    Jugador j = new Jugador("Marta","1234","martavivesluis@gmail.com");
    j.delete();
}
public void testUpdate() throws Exception {
Jugador j = new Jugador();
j.setEmail("martavivesluis@gmail.com");
j.select("email",j.getEmail());
j.setContrasenya("123456");
j.update();

}
    public void testIdEsDiferentInsertantDosUsuaris()
    {
        try {
            Jugador jugador = new Jugador("Joan","1234","s33@hola.com");
            Jugador jugador2 = new Jugador("Sonia","1234","swww@hola.com");
            jugador.delete();
            jugador2.delete();
            jugador.insert();
            jugador2.insert();
            jugador2.setEmail("martavivesluis@gmail.com");
            //jugador2.updateQuery();
            assertThat(jugador.getId(), is(not(jugador2.getId())));
            assertEquals(jugador.getId(), jugador.getEmail().hashCode());
            assertEquals(jugador2.getId(), jugador2.getEmail().hashCode());
            System.out.println("p1"+ jugador.getId()+"  p2"+ jugador2.getId());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
    public void testusuariRegistrat()throws Exception {

    }
    public void testLogin() throws Exception {
        Jugador k = new Jugador("Joan22","1234" ,"s2@hola.com");
        k.insert();


        Jugador j = new Jugador();
        j.select("email", "s2@hola.com");

        assertEquals(j.getContrasenya(),"1234");

        j.delete();

    }

    public void testSelectByName() throws Exception {
        Jugador j = new Jugador();
        j.select("nom", "mariantonia");
        assertEquals(j,null);

    }


    public void testActualitzarUsuari()
    {
        try {
        Personatge p = new Personatge("pirata",4,4,4,4,4);
        p.insert();



    }
    catch(Exception e){e.printStackTrace();}
    }

    public void testSelect() {
        try {
            Jugador jugador = new Jugador("Joan","1234","s@hola.com");
            //jugador.setId(-1356844420);
            //System.out.println("empezamos");
            jugador.loguejarUsuari("1234","s@hola.com");
            //jugador.insert();
            //jugador.actualitzarDades(jugador.getId(),"micasa");
//            assertEquals(jugador.getEmail(),"s@hola.com");
            //jugador.select();
        }
        catch (Exception e)
        {
            e.printStackTrace();
//            fail();
        }
    }
    public void testPersonaje(){
        Personatge mipersonaje = new Personatge("minion",5,6,7,8,1);
        Personatge mipersonaje2 = new Personatge("batman",1,2,3,4,0);
        Personatge mipersonaje3 = new Personatge("spiderman",1,2,3,4,2);

    try {
        mipersonaje.insert();mipersonaje2.insert();mipersonaje3.insert();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
