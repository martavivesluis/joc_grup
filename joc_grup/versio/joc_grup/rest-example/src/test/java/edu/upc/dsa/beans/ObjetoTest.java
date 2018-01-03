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
        relacioPersonatgeObjecte mirelacio = new relacioPersonatgeObjecte(5,79);
        relacioPersonatgeObjecte mirelacio2 = new relacioPersonatgeObjecte(10,79);

        try {
            mirelacio.insert();mirelacio2.insert();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void testSelect(){
        Objeto miobjeto = new Objeto();
        try {
            miobjeto.select("id","18");
            miobjeto.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
