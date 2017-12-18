package edu.upc.dsa.beans;

import junit.framework.TestCase;

public class MonstruoTest extends TestCase {
    public void testAddMonsters() throws Exception{
        Monstruo a = new Monstruo("gnomo",2,3,1);
        Monstruo b = new Monstruo("bruja",2,3,1);
        Monstruo c = new Monstruo("elfo",2,3,1);
        a.insert();
        b.insert();
        c.insert();}
}

