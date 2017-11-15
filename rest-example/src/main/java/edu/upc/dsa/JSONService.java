package edu.upc.dsa;

import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/json")//porta
public class JSONService {
    SingletonMundo instanciaUnica = SingletonMundo.getInstance();
    public JSONService()
    {
        //creació d'un jugador
        Jugador j1 = new Jugador();
        j1.setNom("anna");
        j1.setContrasenya("anna");
        //creacio d'un personatge
        Usuario p1 = new Usuario();
        p1.setNombre("Gnomo");
        p1.setAtaque(20);

    }
    @GET
    @Path("/got/{nomJugador}")//variable entra pels corxets
    @Produces(MediaType.APPLICATION_JSON)
        public Jugador getJugador(@PathParam("nomJugador") String nomJugador)
        {

            return instanciaUnica.mundo.getJugador(nomJugador);
        }


    @Path("exception")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String throwIt() throws Exception
    {
        throw new Exception("My Exception");
    }

}