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
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W:
                setAdalt(true);
                break;
            case KeyEvent.VK_S:
                setAbaix(true);
                break;
            case KeyEvent.VK_A:
                setEsquerra(true);
                break;
            case KeyEvent.VK_D:
                setDreta(true);
                break;

        }

        tecles[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_W:
                setAdalt(false);
                break;
            case KeyEvent.VK_S:
                setAbaix(false);
                break;
            case KeyEvent.VK_A:
                setEsquerra(false);
                break;
            case KeyEvent.VK_D:
                setDreta(false);
                break;

        }


        tecles[e.getKeyCode()] = false;
    }


    @Override
    //tecla picada i l'hem soltat
    public void keyTyped(KeyEvent e) {


    }


    public void setAdalt(boolean adalt) {
        this.adalt = adalt;
    }

    public void setAbaix(boolean abaix) {
        this.abaix = abaix;
    }

    public void setEsquerra(boolean esquerra) {
        this.esquerra = esquerra;
    }

    public void setDreta(boolean dreta) {
        this.dreta = dreta;
    }
}
