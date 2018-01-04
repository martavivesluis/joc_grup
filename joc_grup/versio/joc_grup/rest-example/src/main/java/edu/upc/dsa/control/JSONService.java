package edu.upc.dsa.control;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upc.dsa.DAOG.DAO;
import edu.upc.dsa.DAOG.DAOMapa;
import edu.upc.dsa.DAOG.RelacioPersonatgeJugador;
import edu.upc.dsa.DAOG.relacioPersonatgeObjecte;
import edu.upc.dsa.beans.*;
import edu.upc.dsa.beans.mapa.Mapa;

@Path("/json")//porta
public class JSONService {
    Mundo miMundo;
    DAO midao = new DAO();

    public JSONService() {miMundo = Mundo.getIntanceMundo();
    }

    //************REST APUNTANT BASE DE DADES****************

    /**************funcio login +personatges+objectes actuals**************/
    @POST
    @Path("/Jugador/{email}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador Login(@PathParam("email") String email, Login login){

        return miMundo.enter(email,login);
        }

    /*************funcio nou usuari***************************************/
    @POST
    @Path("/newJugador/{email}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador newJugador(@PathParam("email") String email,Login login){

        return miMundo.newGamer(email,login);
    }

   /***************funcio que afegeix objecte a personatge base de dades**********/
    @POST
    @Path("/addObject/{idobject}/{idpersonatge}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addNewObject(Objeto miob,@PathParam("idpersonatge") Integer idp) {
        return miMundo.addObjectPersonatge(miob,idp);}

    /*****************funcio actualització personatge base de dades**********/
    @POST
    @Path("/updatePersonaje")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes({MediaType.APPLICATION_JSON})
    public Personatge updatePersonaje(Personatge p) {
        return miMundo.updatePersonatge(p);
    }

   /******************funcio nova partida*******************/
    @POST
    @Path("/Mapa/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMapa(Personatge personatge) throws Exception{
        return miMundo.newGame(personatge);
    }

    /************ funcio partida començada************/
    @GET
    @Path("/Mapa/{idJugador}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMapa(@PathParam("idJugador")int id) throws Exception{
        return miMundo.saveMap(id);
    }

    /**************************Creacio nova partida**************************/
    @POST
    @Path("/newPartida/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public int addNewPartida(Partida nueva)//1 correctamente creada
    {
        try {
            nueva.insert();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    /***************************Gestion Partidas,resultat estàtic************/
    @GET
    @Path("/Ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Partida> getRanking() {
        return midao.ranking();
    }





    /*******************funcions a revisar i ordenar********************/
    //elimina usuari
    @POST
    @Path("/delete/{email}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Answer deleteJugador(@PathParam("email") String email, Login login){
        System.out.println("Eliminem  a "+login.getNom());
        Jugador j = new Jugador(login.getNom(),login.getPassword(),email);
        Answer mianswer = new Answer();
        if(j.delete()==true){
            mianswer.setResposta("OK");
            return mianswer;}
        mianswer.setResposta("KO");
        return mianswer;

    }

    //actualitzar contrassenya o nom
    @POST
    @Path("/update/{email}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Answer updateJugador(@PathParam("email") String email, Login login){
        System.out.println(email);

        Jugador j = new Jugador(login.getNom(),login.getPassword(),email);
        Answer mianswer = new Answer();
        mianswer.setResposta("KO");
        try {
            if(j.update() == true)
            {
                mianswer.setResposta("OK");
                return mianswer;
            }

            else{
                return mianswer;}
        } catch (Exception e) {
            e.printStackTrace();
            return mianswer;

        }

    }
    //********************************************************
    //Assosiació nou personatge a jugador
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
            RelacioPersonatgeJugador r = new RelacioPersonatgeJugador(Integer.parseInt(id),p.getId());
            r.insert();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //******************************************************************
    //LListat personatges disponibles -> Jugador
    @GET
    @Path("/Personajes/{idjugador}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador personajes(@PathParam("idjugador") String id)
    {
        int idJugador =  Integer.parseInt(id);
        System.out.println(idJugador);
        Jugador j = new Jugador();
        try {
            j.select("id",id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j.seleccionarPersonajes(j);
    }
}




