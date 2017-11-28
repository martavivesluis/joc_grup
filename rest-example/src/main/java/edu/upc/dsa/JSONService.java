package edu.upc.dsa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/json")//porta
public class JSONService {
SingletonMundo mimundo;
    public JSONService()
    {
       mimundo = SingletonMundo.getInstance();

    }
    @GET
    @Path("/Jugador/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador buscarJugador(@PathParam("nombre") String nombre) {
        return mimundo.mundo.getJugador(nombre);
    }


    /*


    @GET
    @Path("/Jugador/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador buscarJugador(@PathParam("nombre") String nombre) {
        return jugadores.getMapaJugadores().get(nombre);
    }

    @GET
    @Path("/Personaje/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personatge buscarPersonaje(@PathParam("nombre") String nombre) {
        return jugadores.get().get(nombre);
    }

    @GET
    @Path("/Objeto/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Objeto buscarObjeto(@PathParam("nombre") String nombre) {
        return jugadores.getMapaObjetos().get(nombre);
    }


    @POST
    @Path("/nuevoJugador/{jugadorNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoJugador(Jugador jugador, @PathParam("jugadorNombre") String nombre) {
        jugador.setNombre(nombre);
        jugadores.getMapaJugadores().put(nombre, jugador);
        return Response.status(201).entity("Añadido jugador con nombre:" +nombre).build();
    }

    @POST
    @Path("/eliminarJugador/{jugadorNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarJugador(@PathParam("jugadorNombre") String nombre) {
        jugadores.getMapaJugadores().remove(nombre);
        return Response.status(201).entity("Eliminado jugador con nombre:" +nombre).build();
    }

    @POST
    @Path("/nuevoPersonaje/{personajeNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoPersonaje(Personatge personaje, @PathParam("personajeNombre") String nombre) {
        personaje.setNombre(nombre);
        jugadores.getMapaPersonajes().put(nombre, personaje);
        return Response.status(201).entity("Añadido personaje con nombre:" +nombre).build();
    }

    @POST
    @Path("/eliminarPersonaje/{personajeNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarPersonaje(@PathParam("personajeNombre") String nombre) {
        jugadores.getMapaPersonajes().remove(nombre);
        return Response.status(201).entity("Eliminado personaje con nombre:" +nombre).build();
    }

    @POST
    @Path("/nuevoObjeto/{objetoNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoObjeto(Objeto objeto, @PathParam("objetoNombre") String nombre) {
        objeto.setNombre(nombre);
        jugadores.getMapaObjetos().put(nombre, objeto);
        return Response.status(201).entity("Añadido objeto con nombre:" +nombre).build();
    }

    @POST
    @Path("/eliminarObjeto/{objetoNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarObjeto(@PathParam("objetoNombre") String nombre) {
        jugadores.getMapaObjetos().remove(nombre);
        return Response.status(201).entity("Eliminado objeto con nombre:" +nombre).build();
    }

    @POST
    @Path("/nuevoObjetoPersonaje/{personajeNombre}/{objetoNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoObjetoPersonaje(@PathParam("personajeNombre") String nombrePersonaje,
                                         @PathParam("objetoNombre") String nombreObjeto) {

        //Añadir comprobaciones de errores y tal
        jugadores.getMapaPersonajes().get(nombrePersonaje).getObjetos().add(jugadores.getMapaObjetos().get(nombreObjeto));

        return Response.status(201).entity("Añadido objeto con nombre:" +nombreObjeto +"a personaje" +nombrePersonaje).build();
    }

    @POST
    @Path("/nuevoPersonajeJugador/{jugadorNombre}/{personajeNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoPersonajeJugador(@PathParam("jugadorNombre") String jugadorNombre,
                                          @PathParam("personajeNombre") String personajeNombre) {

        //Añadir comprobaciones de errores y tal
        jugadores.getMapaJugadores().get(jugadorNombre).getPersonajes().add(jugadores.getMapaPersonajes().get(personajeNombre));

        return Response.status(201).entity("Añadido personaje con nombre:" +personajeNombre +"a jugador" +jugadorNombre).build();
    }




    @Path("exception")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String throwIt() throws Exception
    {
        throw new Exception("My Exception");
    }
    */

}