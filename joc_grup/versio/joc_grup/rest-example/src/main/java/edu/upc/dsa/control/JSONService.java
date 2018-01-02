package edu.upc.dsa.control;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upc.dsa.DAOG.DAO;
import edu.upc.dsa.DAOG.RelacioPersonatgeJugador;
import edu.upc.dsa.DAOG.relacioPersonatgeObjecte;
import edu.upc.dsa.beans.*;

@Path("/json")//porta
public class JSONService {

    //iiPara los metodos de añadir jugador/personaje/objeto se podrian quitar los parametros y llamar al constructor?
    //Añadir comprobaciones y comprobar comportamiento en caso de error/que no exista algo

    Mundo miMundo;
    DAO midao = new DAO();

    public JSONService() {miMundo = Mundo.getIntanceMundo();
    }

    //************REST APUNTANT BASE DE DADES****************

    //servei d'autentificació login funcionant, tornant personatjes+objectes
    @POST
    @Path("/Jugador/{email}")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador Login(@PathParam("email") String email, Login login){
        System.out.println(login.getPassword());
        Jugador j = new Jugador();

        try{
            j.select("email",email);
            j.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        if(j.getContrasenya().equals(login.getPassword()))
        {
            j.seleccionarPersonajes(j);
            return j;
        }
        return null;
    }

    //nou usuari
    @POST
    @Path("/newJugador/{email}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Jugador newJugador(@PathParam("email") String email,Login login){
        System.out.println("registrem a "+login.getNom());
        Jugador j = new Jugador(login.getNom(),login.getPassword(),email);
        try {
            j.insert();
        } catch (Exception e) {
            return null;
        }
        return j;
    }

    //elimina usuari
    @POST
    @Path("/delete/{email}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Answer deleteJugador(@PathParam("email") String email, Login login){
        System.out.println("registrem a "+login.getNom());
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

    //añadir objeto a personaje
    @POST
    @Path("/addObject/{idobject}/{idpersonatge}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personatge addNewObject(Objeto miob,@PathParam("idpersonatge") Integer idp)
    {
        //TODO: implementar añadir objeto (Marta)
        //comprovem que esta a la base de dades l'objecte
        try{
            Objeto miobjeto = new Objeto();
            miobjeto.select("id",""+miob.getId());
            if(miobjeto==null) {//objecto inexistente añadimos
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        relacioPersonatgeObjecte mirelacion = new relacioPersonatgeObjecte();

        Personatge mipersonatge = new Personatge();


        return null;
    }
    //nova partida


    @POST
    @Path("/newPartida/")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public int addNewPartida(Partida nueva)//1 correctamente creada
    {
    try {
        nueva.insert();
        //TODO: quan nova partida read mapa from file and delete mapa/jugador de la base de  // 20 mins
        return 1;
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
}

//TODO: update personatje  (POST QUE REP UN JSON DE PERSONATGE) (Marta)
@GET
@Path("/updatePersonaje")
@Produces(MediaType.APPLICATION_JSON)
public Personatge updatePersonaje(Personatge p) {
    try {
        boolean update = p.update();//true hi ha hagut modificacio, false no hi ha hagut modificació
        if(update == true){return p;}
        return null;
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}







    



// TODO: update Mapa // OJO !!!
// TODO: store in database (DAO) taula FET

    /// cella-jug-obj
    /// Lau - c f- Obj - Tipo

     ///    idjug columna fila idElement tipus
    ///  12    1         3     15     Monstruo / Objeto / Personaje

//            {{c1}c2}c3{@Jugador j1}}}}


    @GET
    @Path("/Mapa")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMapa() throws Exception{

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return mapper.writeValueAsString( miMundo.mapa); //  mirate esta linea MARTA por que he cambiado el singletone
    }

    /*
    public List<Partida> partidas () {
        List<Partida> partides = new ArrayList<Partida>();
        partides.add(new Partida("1", "PArtida 1", 7777));
        partides.add(new Partida("2", "PArtida 2", 3));
        partides.add(new Partida("3", "PArtida 3",3333));
    }*/
/***************************Gestion Partidas**********************************/
    @GET
    @Path("/Ranking")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Partida> getRanking() {
        return midao.ranking();
    }

}




