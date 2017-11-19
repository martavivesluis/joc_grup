package edu.upc.dsa.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclat implements KeyListener{

    //aquest numero es més aviat els caracters que representa, ja miraré quin es. no idea
    private final static int numTecles = 120;
    private final boolean[] tecles = new boolean[numTecles];


    //control de moviment, creació de moviment
    public boolean adalt;
    public boolean abaix;
    public boolean esquerra;
    public boolean dreta;

    public void actualizar()//ho tenim en joc
    {
        //correspondecia de tecles
        adalt= tecles[KeyEvent.VK_W];
        abaix= tecles[KeyEvent.VK_S];
        esquerra= tecles[KeyEvent.VK_A];
        dreta= tecles[KeyEvent.VK_D];

    }
    @Override
    //tecla picada i no la hem soltat
    public void keyPressed(KeyEvent e) {
        tecles[e.getKeyCode()] = true;
    }

    @Override
    //tecla lliberada
    public void keyReleased(KeyEvent e) {
        tecles[e.getKeyCode()] = false;
    }


    @Override
    //tecla picada i l'hem soltat
    public void keyTyped(KeyEvent e) {


    }






}
