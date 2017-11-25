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



<<<<<<< HEAD
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                setAdalt(true);
            case KeyEvent.VK_S:
                setAbaix(true);
            case KeyEvent.VK_A:
                setEsquerra(true);
            case KeyEvent.VK_D:
                setDreta(true);
        }
    }
    public boolean isAdalt() {
        return adalt;
    }

    public void setAdalt(boolean adalt) {
        this.adalt = adalt;
    }

    public boolean isAbaix() {
        return abaix;
    }

    public void setAbaix(boolean abaix) {
        this.abaix = abaix;
    }

    public boolean isEsquerra() {
        return esquerra;
    }

    public void setEsquerra(boolean esquerra) {
        this.esquerra = esquerra;
    }

    public boolean isDreta() {
        return dreta;
    }

    public void setDreta(boolean dreta) {
        this.dreta = dreta;
    }
=======



>>>>>>> fd3fdfa2e2dc7b862791ae7089e9746a9880aba2
}
