package edu.upc.dsa;

import javax.ws.rs.*;
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
        instanciaUnica.mundo.crearJugador(j1);
        //creacio d'un personatge
        Personatge p1 = new Personatge();
        p1.setNombre("Gnomo");
        p1.setAtaque(20);
        instanciaUnica.mundo.AfegirPersonatgeJugador(j1,p1);

    }
    @GET
    @Path("/got/{nomJugador}")//variable entra pels corxets
    @Produces(MediaType.APPLICATION_JSON)
        public Jugador getJugador(@PathParam("nomJugador") String nomJugador)
        {
            return instanciaUnica.mundo.getJugador(nomJugador);
        }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void añadirObjetoAUsuario(Personatge u, Objeto o) {

        u.getArrMisObjetos().add(o);
    }








    @Path("exception")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String throwIt() throws Exception
    {
        throw new Exception("My Exception");
    }

}