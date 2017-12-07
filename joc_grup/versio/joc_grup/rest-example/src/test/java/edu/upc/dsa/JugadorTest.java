package edu.upc.dsa;

import edu.upc.dsa.DAOG.DAO;
import edu.upc.dsa.DAOG.DAO_InterfaceUserImp;
import edu.upc.dsa.Jugador;
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
            assertThat(jugador.getId(), is(not(0)));
            System.out.println("p1"+ jugador.getId());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail();
        }
    }
public void testDelete()
{
    Jugador j = new Jugador("Marta","1234","martavivesluis@gmail.com");
    j.delete();
}

    public void testIdEsDiferentInsertantDosUsuaris()
    {
        try {
            Jugador jugador = new Jugador("Joan","1234","s@hola.com");
            Jugador jugador2 = new Jugador("Sonia","1234","swww@hola.com");
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
        Jugador j = new Jugador();
        j.select("email", "s@hola.com");

        assertEquals(j.getContrasenya(),"3465");

    }

    public void testSelectByName() throws Exception {
        Jugador j = new Jugador();
        j.select("nom", "Sonia");

        assertEquals(j.getContrasenya(),"1234");

    }


    public void testActualitzarUsuari()
    {
        try {
        Jugador jugador = new Jugador("Marta", "3465", "s@hola.com");
       //jugador.update();
            assertEquals(jugador.loguejarUsuari("3465","s@hola.com"),false);


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

}
