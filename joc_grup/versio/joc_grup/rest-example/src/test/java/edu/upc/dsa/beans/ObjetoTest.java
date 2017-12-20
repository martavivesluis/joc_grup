package edu.upc.dsa.beans;

import edu.upc.dsa.DAOG.relacioPersonatgeObjecte;
import junit.framework.TestCase;

public class ObjetoTest extends TestCase{
    public void testAddObjects() throws Exception{
        Objeto a = new Objeto("espada","ataque","destruirá a todos los enemigos",2);
        Objeto b = new Objeto("escudo","defensa","protegerse contra los enemigos",4);
        Objeto c = new Objeto("casco","defensa","protegerse contra los enemigos",7);
        a.insert();
        b.insert();
        c.insert();}

    public void testRelacio(){
        relacioPersonatgeObjecte mirelacio = new relacioPersonatgeObjecte(5,73);
        try {
            mirelacio.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
