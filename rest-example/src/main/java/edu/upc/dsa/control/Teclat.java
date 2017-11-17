package edu.upc.dsa.control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class Teclat implements KeyListener{

    //aquest numero es més aviat els caracters que representa, ja miraré quin es. no idea
    private final static int numTecles = 120;
    private final boolean[] teclas = new boolean[numTecles];


    //control de moviment, creació de moviment
    public boolean adalt;
    public boolean abaix;
    public boolean esquerra;
    public boolean dreta;

    public void actualizar()//ho tenim en joc
    {
        //correspondecia de tecles
        adalt=teclas[KeyEvent.VK_W];
        abaix=teclas[KeyEvent.VK_S];
        esquerra=teclas[KeyEvent.VK_A];
        dreta=teclas[KeyEvent.VK_D];

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
