package edu.upc.dsa;
import junit.framework.TestCase;

import static junit.framework.Assert.assertEquals;

public class JugadorTest extends TestCase {

    public void testCreacioJugador()
    {
        try {
            SingletonMundo instanciaunica = SingletonMundo.getInstance();
            Personatge personatge = new Personatge("Gnomo", "atacant", 10,20, 30,40);
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



}
