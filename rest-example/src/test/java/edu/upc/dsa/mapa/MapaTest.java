package edu.upc.dsa.mapa;

import junit.framework.TestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MapaTest extends TestCase {
    public void testCreateMapaDimensions() throws Exception {
        Mapa m = new Mapa(5,7);
        assertEquals(m.doGetWidth(),5);
        assertEquals(m.doGetHeight(),7);
    }
    public void testPutElementInMap() throws Exception {
        Mapa m = new Mapa(5,7);
        EmptyCell drawable1 = new EmptyCell();
        m.putElement(3,4,drawable1);
        Drawable ans = m.doGetElement(3,4);
        Drawable ans2 = m.doGetElement(4,4);

        assertEquals(ans,drawable1);
        assertThat(ans2, is(not(drawable1)));
    }

}
