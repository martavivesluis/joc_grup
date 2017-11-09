package edu.upc.dsa;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




@Path("/json")
public class JSONService {
    private Mundo mundo =  SingletonMundo.getInstance().mundo;

    public JSONService() {

    }
    @POST
    @Path("usuario/new")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newTrack(Usuario user) {
       mundo.crearUsuario(user);
       return Response.status(201).entity("User added!").build();
    }

    @GET
    @Path("/usuario/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getTrack(@PathParam("nombre") String nombre) {
        return mundo.consultarUsuario(nombre);
    }
/*
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Track> getAllTracks() {
        ArrayList<Track> tracks = SingletonMundo.getInstance().array;
        return tracks;
    }


    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrackInJSON() {

        Track track = new Track();
        track.setTitle("Enter Sandman");
        track.setSinger("Metallica");

        return track;

    }


    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteTrack(Track track) {
        ArrayList<Track> tracks = SingletonMundo.getInstance().array;
        tracks.remove(track);
        // Atencion: siempre añade en la misma posicion por el scope de tracks
        return Response.status(201).entity("Track added in position "+tracks.size()).build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(Track track) {

        ArrayList<Track> tracks = SingletonMundo.getInstance().array;;
        String result = "Track saved in array : " + track;
        tracks.add(track);
        return Response.status(201).entity(result).build();
    }
*/

}