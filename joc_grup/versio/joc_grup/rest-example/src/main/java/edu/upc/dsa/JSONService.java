package edu.upc.dsa;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upc.dsa.mapa.Mapa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("json")//porta
public class JSONService {
    BDTemporal BDtemp;
    SingletonMundo mimundo;

    public JSONService() {
        mimundo = SingletonMundo.getInstance();
        Jugador j = new Jugador("marta","1234","martavivesluis@gmail.com");
        mimundo.mundo.jugadores.put(j.getId(),j);
        Personatge p =  new Personatge("Hamlet",1,1,1,1);
        mimundo.mundo.afegirPersonatgeJugador(j,p);
    }
    //************REST APUNTANT BASE DE DADES****************
    //servei d'autentificació login
    @POST
    @Path("/Jugador/{email}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador Login(@PathParam("email") String email,Login login){
        System.out.println(login.getPassword());
        Jugador j = new Jugador();

        try{
            j.select("email",email);
            j.toString();
        }
        catch(Exception e)
        {
            return null;
        }
        if(j.getContrasenya().equals(login.getPassword()))
        {
            return j;
        }
        return null;
    }

    //Creacio personatgeeeee
    @POST
    @Path("/Jugador/{nomPersonatge}/{idjugador}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public void newPersonaje(@PathParam("nomPersonatge") String nomPersonatge,@PathParam("idjugador") String id){
        System.out.println(nomPersonatge);
        Personatge p = new Personatge();
        p.setNombre(nomPersonatge);
        try {
            p.insert();
            relacio r = new relacio(Integer.parseInt(id),p.getId());
            r.insert();


        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    //******************************************************************

    @GET
    @Path("/Personaje/{nombrePersonaje}/{idJugador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personatge buscarPersonaje(@PathParam("nombrePersonaje") String nombrePersonaje, @PathParam("idJugador") int id) {
        return new Personatge("Hamlet",1,1,1,1);
    }

    @GET
    @Path("/Mapa")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMapa() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return mapper.writeValueAsString( mimundo.mundo.mapa);
    }

}
   /* @GET
    @Path("/Jugador/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador buscarJugador(@PathParam("nombre") String nombre) {
        return BDtemp.getMapaJugadores().get(nombre);
    }

    @GET
    @Path("/Personaje/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personatge buscarPersonaje(@PathParam("nombre") String nombre) {
        return BDtemp.getMapaPersonajes().get(nombre);
    }

    @GET
    @Path("/Objeto/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Objeto buscarObjeto(@PathParam("nombre") String nombre) {
        return BDtemp.getMapaObjetos().get(nombre);
    }


    @POST
    @Path("/nuevoJugador/{jugadorNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoJugador(Jugador jugador, @PathParam("jugadorNombre") String nombre) {
        jugador.setNom(nombre);
        BDtemp.getMapaJugadores().put(nombre, jugador);
        return Response.status(201).entity("Añadido jugador con nombre:" +nombre).build();
    }

    @POST
    @Path("/eliminarJugador/{jugadorNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarJugador(@PathParam("jugadorNombre") String nombre) {
        BDtemp.getMapaJugadores().remove(nombre);
        return Response.status(201).entity("Eliminado jugador con nombre:" +nombre).build();
    }

    @POST
    @Path("/nuevoPersonaje/{personajeNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoPersonaje(Personatge personaje, @PathParam("personajeNombre") String nombre) {
        personaje.setNombre(nombre);
        BDtemp.getMapaPersonajes().put(nombre, personaje);
        return Response.status(201).entity("Añadido personaje con nombre:" +nombre).build();
    }

    @POST
    @Path("/eliminarPersonaje/{personajeNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarPersonaje(@PathParam("personajeNombre") String nombre) {
        BDtemp.getMapaPersonajes().remove(nombre);
        return Response.status(201).entity("Eliminado personaje con nombre:" +nombre).build();
    }

    @POST
    @Path("/nuevoObjeto/{objetoNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoObjeto(Objeto objeto, @PathParam("objetoNombre") String nombre) {
        objeto.setNombre(nombre);
        BDtemp.getMapaObjetos().put(nombre, objeto);
        return Response.status(201).entity("Añadido objeto con nombre:" +nombre).build();
    }

    @POST
    @Path("/eliminarObjeto/{objetoNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarObjeto(@PathParam("objetoNombre") String nombre) {
        BDtemp.getMapaObjetos().remove(nombre);
        return Response.status(201).entity("Eliminado objeto con nombre:" +nombre).build();
    }

    @POST
    @Path("/nuevoObjetoPersonaje/{personajeNombre}/{objetoNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoObjetoPersonaje(@PathParam("personajeNombre") String nombrePersonaje,
                                         @PathParam("objetoNombre") String nombreObjeto) {

        //Añadir comprobaciones de errores y tal
        BDtemp.getMapaPersonajes().get(nombrePersonaje).getArrMisObjetos().add(BDtemp.getMapaObjetos().get(nombreObjeto));

        return Response.status(201).entity("Añadido objeto con nombre:" +nombreObjeto +"a personaje" +nombrePersonaje).build();
    }

    @POST
    @Path("/nuevoPersonajeJugador/{jugadorNombre}/{personajeNombre}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nuevoPersonajeJugador(@PathParam("jugadorNombre") String jugadorNombre,
                                          @PathParam("personajeNombre") String personajeNombre) {

        //Añadir comprobaciones de errores y tal
        BDtemp.getMapaJugadores().get(jugadorNombre).getPersonatges().add(BDtemp.getMapaPersonajes().get(personajeNombre));

        return Response.status(201).entity("Añadido personaje con nombre:" +personajeNombre +"a jugador" +jugadorNombre).build();
    }



    /*  import javax.ws.rs.core.Response;
*/
