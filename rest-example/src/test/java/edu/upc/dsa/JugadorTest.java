package edu.upc.dsa;
import junit.framework.TestCase;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class JugadorTest extends TestCase {

    public void testCreacioJugador()
    {
        try {
            SingletonMundo instanciaunica = SingletonMundo.getInstance();
            Personatge personatge = new Personatge("Gnomo", 10,20, 30,40);
            instanciaunica.mundo.crearUsuario(personatge);
            Jugador Anna = new Jugador("Anna", "marianet", "marianet9990");
            instanciaunica.mundo.crearJugador(Anna);
            instanciaunica.mundo.AfegirPersonatgeJugador(Anna, personatge);
            //comprovació nom jugador
            assertEquals( "Anna", instanciaunica.mundo.getJugador("Anna").nom );

            assertEquals( "Gnomo", instanciaunica.mundo.consultarPersonatgesJugador(Anna).get(0) );
            //comprovacio personatge afegit a jugador

          // assertEquals( "Gnomo", instanciaunica.mundo.consultarPersonatgeJugador(Anna) );


        }
        catch (Exception e)
        {
            fail();
        }
    }

    public void idEs0quanSinicialitza()
    {
        try {
            Personatge personatge = new Personatge("Gnomo", 10,20, 30,40);
            assertEquals( 0, personatge.getId() );
        }
        catch (Exception e)
        {
            fail();
        }
    }

    public void testIdEsDiferentDe0QuanEsGuardaABd()
    {
        try {
            Personatge personatge = new Personatge("Gnomo", 10,20, 30,40);
            personatge.insert();
            assertThat(personatge.getId(), is(not(0)));
        }
        catch (Exception e)
        {
            fail();
        }
    }

    public void testIdEsDiferentInsertantDosPersonatges()
    {
        try {
            Personatge personatge = new Personatge("Gnomo", 10,20, 30,40);
            Personatge personatge2 = new Personatge("Gnomo2", 102,220, 320,420);
            personatge.insert();
            personatge2.insert();
            assertThat(personatge.getId(), is(not(personatge2.getId())));
        }
        catch (Exception e)
        {
            fail();
        }
    }

}
