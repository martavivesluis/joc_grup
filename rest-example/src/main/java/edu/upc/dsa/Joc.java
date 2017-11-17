package edu.upc.dsa;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;

public class Joc extends Canvas implements Runnable{

    private static JFrame finestra;

    //segon thread
    private static Thread thread;

    //marco com a final perque el tamany no canvie en tota l'execució
    private static final int AMPLADA = 800;
    private static final int ALÇADA = 800;
    private static final String NOMBRE = "Joc";
    private static int aps = 0;
    private static int fps = 0;
    private static volatile boolean funcionant = false;

    private Joc()//la faig privada perquè només aquesta classe pugui crear instancies
    {
        //CONSTRUCTOR
        setPreferredSize(new Dimension(AMPLADA, ALÇADA));
        //iniciliatzació
        finestra = new JFrame(NOMBRE);
        //botó tancar aplicació quan tanquem finestra
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //no canviar tamany de la finestra
        finestra.setResizable(false);
        //afegir un disseny de la finestra, organització interna li passem un gestor
        finestra.setLayout(new BorderLayout());
        //afegim a la finestra el nostre joc = canvas
        finestra.add(this, BorderLayout.CENTER);
        finestra.pack();//per ajustar el tamany a 800*800
        finestra.setLocationRelativeTo(null); //fixa finestra al mig de l'escriptori
        finestra.setVisible(true);
    }

    public static void main(String[] args)
    {
        //creació instancia de la classe
        Joc joc = new Joc();
        //thread per dibuixar els gràfics de forma automàtica sense afectar el main
        joc.inici();

    }

    public synchronized void inici()
    {
        funcionant = true;
        //li hem de passar la classe que va a executar (this)
        thread = new Thread(this, "grafics");
        thread.start();
    }
    public synchronized void fi()
    {
        try
        {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void actualitzar()
    {
        aps++;
        fps++;
    }
    private void mostrar()
    {
        aps++;
        fps++;
    }




    @Override//m'ho ha creat desprès de fer l'implemnts runable,
    // això es lo que executa se segon thread(grafics)
    public void run()
    {
        final int NS_PERSEGON = 1000000000; //equivalencia
        final byte ACTUALITZACIONSEGON = 60;
        //regla de tres, quants ns per actualitzacio
        final double NS_PERACTUALITZACIO= NS_PERSEGON / ACTUALITZACIONSEGON;

        //s'assigna quantitat de temps exacte en aquest moment
        long referenceActulitzacio = System.nanoTime();
        long referenciaContador = System.nanoTime();

        double  tempsPassat;
        double  delta= 0; //quantitat de temps que ha passat fins una actualitzacio


        System.out.println("se esta executant");
        while(funcionant)
        {

            final long iniciBucle = System.nanoTime(); //inicia cronometro
            tempsPassat = iniciBucle - referenceActulitzacio;
            referenceActulitzacio = iniciBucle;
            delta = delta  + (tempsPassat / NS_PERACTUALITZACIO);
            while (delta >=1) {
                actualitzar();
                delta--;
            }

            mostrar();
            //OBJETIVO ACTULITZAR CONTADOR CADA SEGON
            if(System.nanoTime() - referenciaContador > NS_PERSEGON)
            {
                //escriure-ho en la capçalera de la finestra
                finestra.setTitle(NOMBRE + " || aps:" +aps + "|| fps:"+fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
}
