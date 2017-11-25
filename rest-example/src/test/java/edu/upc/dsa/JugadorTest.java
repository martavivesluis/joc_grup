package edu.upc.dsa;
import edu.upc.dsa.DAOG.DAO;
import junit.framework.TestCase;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JugadorTest extends TestCase {


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


    public void testIdEsDiferentInsertantDosUsuaris()
    {
        try {
            Jugador jugador = new Jugador("Joan","1234","s@hola.com");
            Jugador jugador2 = new Jugador("Sonia","1234","swww@hola.com");
            jugador.insert();
            jugador2.insert();
            jugador2.setEmail("martavivesluis@gmail.com");
            jugador2.updateQuery();
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

    public void testSelect() {
        try {
            Jugador jugador = new Jugador();
            jugador.setId(-1356844420);
            System.out.println("empezamos");
            jugador.update();
            jugador.actualitzarDades(jugador.getId(),"micasa");
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
