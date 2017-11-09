package edu.upc.dsa;

import java.util.ArrayList;
import java.util.HashMap;

public class SingletonMundo {
    public Mundo mundo;
    static private SingletonMundo instancia_unica;

    private SingletonMundo(){
        mundo = new Mundo();
    }

    static SingletonMundo getInstance(){
       if(instancia_unica == null){
           instancia_unica = new SingletonMundo();
       }
        return instancia_unica;
    }

}
