package edu.upc.dsa;

import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/json")
public class JSONService {
    public JSONService() {


     Mundo mundo = SingletonMundo.getInstance().mundo;
    //creació d'un jugador
    Jugador j1 = new Jugador();
    j1.setNom("anna");
    j1.setContrasenya("anna");

    }
    @GET
    @Path("/got/{nom}")//variable entra pels corxets
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt()
    {
        return "Got it";
    }
    @Path("jugadors/{jugadorName}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getUser(@PathParam("jugadorName") String jugadorName) {
        return "Benvingut " + jugadorName;

    }

    @Path("exception")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String throwIt() throws Exception
    {
        throw new Exception("My Exception");
    }

}