package edu.upc.dsa.control.pintador.per_consola;

import edu.upc.dsa.beans.Personatge;
import edu.upc.dsa.beans.mapa.Mapa;
import edu.upc.dsa.control.pintador.Pintador;
import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class PrintadorPerConsolaImplTest extends TestCase {

    public void testprintMap1test() throws Exception {
        Mapa m = new Mapa(4,4);
        Personatge drawable1 = new Personatge();
        Pintador p = new PrintadorPerConsolaImpl();
        String resultat = p.print(m);
        assertEquals(resultat,"....\n"+
                                    "....\n"+
                                    "....\n"+
                                    "....\n");
    }
    public void testprintMap2test() throws Exception {
        Mapa m = new Mapa(4,4);
        Personatge drawable1 = new Personatge();
        m.putElement(1,2,drawable1);
        Pintador p = new PrintadorPerConsolaImpl();
        String resultat = p.print(m);
        assertEquals(resultat,
                "....\n"+
                "....\n"+
                ".O..\n"+
                "....\n");
    }

    public void testprintMap3test() throws Exception {
        Mapa m = new Mapa(4,4);
        Personatge drawable1 = new Personatge();
        m.putElement(1,2,drawable1);
        Pintador p = new PrintadorPerConsolaImpl();
        String resultat = p.print(m);
        assertEquals(resultat,
                "....\n"+
                        "....\n"+
                        ".O..\n"+
                        "....\n");
        m.moure(1,0,drawable1);
        assertEquals(p.print(m),
                "....\n"+
                        "....\n"+
                        "....\n"+
                        ".O..\n");

    }


}
