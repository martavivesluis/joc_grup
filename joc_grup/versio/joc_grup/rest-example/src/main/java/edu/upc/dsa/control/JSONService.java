package edu.upc.dsa.control;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import edu.upc.dsa.DAOG.DAO;
import edu.upc.dsa.DAOG.DAOMapa;
import edu.upc.dsa.DAOG.RelacioPersonatgeJugador;
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
    @Path("/addObject/{idpersonatge}")
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
    @Path("/NewMapa/{idJugador}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getNewMapa(Personatge personatgeEnviatPelClient, @PathParam("idJugador")int idJugador) throws Exception{
        int nivellPrevio = personatgeEnviatPelClient.getNivel();
        boolean okselect = personatgeEnviatPelClient.select();

        if(!okselect || personatgeEnviatPelClient.getNivel() != nivellPrevio) {
            personatgeEnviatPelClient.subirNivelBaseDeDatos(personatgeEnviatPelClient.getId(),nivellPrevio);
        }
        Jugador j = new Jugador();
        j.setId(idJugador);
        j.select();
        j.selectPersonatgesFromDb(j);
        boolean existeix = false;
        for( Personatge p : j.getPersonatges()){
            if(p.id == personatgeEnviatPelClient.id){
                personatgeEnviatPelClient = p;
                existeix = true;
            }
        }
        if(!existeix){
            RelacioPersonatgeJugador p2j = new RelacioPersonatgeJugador();
            p2j.idPersonatge = personatgeEnviatPelClient.id;
            p2j.idJugador = idJugador;
            p2j.insert();
        }
        return miMundo.newGame(personatgeEnviatPelClient,idJugador); //igorat per el client... el client haura de fer un getMapa despres de NewMapa
    }

    /************ funcio partida començada************/
    @GET
    @Path("/Mapa/{idJugador}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMapa(@PathParam("idJugador")int id) {
       return miMundo.loadMapFromDbAndStringifyIt(id);
    }

    /******************** funcio actualitzar personatge***********************/
    @POST
    @Path("/ActualizeTheCharacter")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public String ActualizeTheCharacter(Personatge p){
        return miMundo.actualizarPersonaje(p);

    }


    @POST
    @Path("/SaveMapa/{idJugador}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public int saveMapa(@PathParam("idJugador")int id, Mapa aguardar)//0.txt correctamente creada
    {
        System.out.println("-----------------------OLA-------------------------");
        /*try {
            aguardar.upsert(id);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }*/
        return 0;
    }
    /**************************Creacio nova partida**************************/
    @POST
    @Path("/newPartida/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public int addNewPartida(Partida nueva)//0.txt correctamente creada
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


    @POST
    @Path("/Jugador/{nomPersonatge}/{tipus}/{idjugador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personatge newPersonaje(@PathParam("nomPersonatge") String nomPersonatge, @PathParam("tipus") String type, @PathParam("idjugador") String id){
        System.out.println("nom:"+nomPersonatge+" tipus:"+type+" idjugador:"+id);
        return miMundo.createNewPersonatge(nomPersonatge,type,id);}







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
        return j.selectPersonatgesFromDb(j);
    }
}




