package edu.upc.dsa.control;

import java.util.HashMap;

public class Controlador_Sesions{ //GUARDAREM LLISTAT DE SESIONS ACTUALS+identificador sessio

    HashMap<Integer,SessioClient> actualSessions;
    static Controlador_Sesions controlador_Sesions;
    private Controlador_Sesions(){
        actualSessions = new HashMap<Integer,SessioClient>();
    }
    static Controlador_Sesions getInstance(){
        if(controlador_Sesions == null){
            controlador_Sesions = new Controlador_Sesions();
        }
        return controlador_Sesions;
    }
    public HashMap<Integer, SessioClient> getActualSessions() {
        return actualSessions;
    }
    public void setActualSessions(HashMap<Integer, SessioClient> actualSessions) {
        this.actualSessions = actualSessions;
    }
    public static Controlador_Sesions getControlador_Sesions() {
        return controlador_Sesions;
    }
    public static void setControlador_Sesions(Controlador_Sesions controlador_Sesions) {
        Controlador_Sesions.controlador_Sesions = controlador_Sesions;
    }
}
