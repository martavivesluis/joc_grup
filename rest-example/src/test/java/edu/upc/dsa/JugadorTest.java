package edu.upc.dsa;
import junit.framework.TestCase;

import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class JugadorTest extends TestCase {

    public void testCreacioJugador()
    {
        try {
            SingletonMundo instanciaunica = SingletonMundo.getInstance();
            Usuario personatge = new Usuario("Gnomo", "atacant", 10,20, 30,40);
            instanciaunica.mundo.crearUsuario(personatge);
            Jugador Anna = new Jugador("Anna", "marianet", "marianet9990");
            instanciaunica.mundo.crearJugador(Anna);
            instanciaunica.mundo.AfegirPersonatgeJugador(Anna, personatge);
            //comprovació nom jugador
            assertEquals( "Anna", instanciaunica.mundo.getJugador("Anna").nom );

            assertEquals( 1, instanciaunica.mundo.consultaPersonatgesJugador(Anna).size() );
            //comprovacio personatge afegit a jugador

          // assertEquals( "Gnomo", instanciaunica.mundo.consultarPersonatgeJugador(Anna) );


        }
        catch (Exception e)
        {
            fail();
        }
    }



}
