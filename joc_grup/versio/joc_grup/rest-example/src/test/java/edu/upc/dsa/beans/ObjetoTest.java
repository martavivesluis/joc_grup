package edu.upc.dsa.beans;

import junit.framework.TestCase;

public class ObjetoTest extends TestCase{
    public void testAddObjects() throws Exception{
        Objeto a = new Objeto("espada","ataque","destruirá a todos los enemigos",2);
        Objeto b = new Objeto("escudo","defensa","protegerse contra los enemigos",4);
        Objeto c = new Objeto("casco","defensa","protegerse contra los enemigos",7);
        a.insert();
        b.insert();
        c.insert();}
}
