package edu.upc.dsa;

import edu.upc.dsa.control.Teclat;
import edu.upc.dsa.grafics.Pantalla;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

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

    //
    private static int x=0;
    private static int y=0;

    private static volatile boolean funcionant = false;
    private static Teclat teclat;
    private static Pantalla pantalla;

    //objectes per manipular pixels joc, aqui estem fent una imatge en blanc
    private static BufferedImage imatge = new BufferedImage(AMPLADA, ALÇADA,
                   BufferedImage.TYPE_INT_RGB);
    //estem accedint a la imatge en forma de un array de pixels
    //retorna un array de int que representa els pixels de la imatge
    //getRaster retorna sequencia de pixels
    private static int[] pixels =((DataBufferInt) imatge.getRaster().getDataBuffer()).getData();



    //CONSTRUCTOR
    private Joc()//la faig privada perquè només aquesta classe pugui crear instancies
    {
        //iniciliatzacions
        setPreferredSize(new Dimension(AMPLADA, ALÇADA));
        pantalla = new Pantalla(AMPLADA, ALÇADA);
        teclat = new Teclat();
        addKeyListener(teclat); //aqui li diem a java que detecti les tecles picades en la classe
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
        teclat.actualizar();
        //descobrir tecla polçada
        if(teclat.adalt)
        {
            //log4javatecla picada
            System.out.println("adalt");
            y++;
        }
        if(teclat.abaix)
        {
            //log4javatecla picada
            System.out.println("abaix");
            y--;
        }
        if(teclat.esquerra)
        {
            //log4javatecla picada
            System.out.println("esquerra");
            x++;
        }
        if(teclat.dreta)
        {
            //log4javatecla picada
            System.out.println("dreta");
            x--;
        }

        aps++;

    }
    private void mostrar()
    {
        //estrategia de buffer, sense el buffer obtindriem la pantalla i la dibuixariem
        //així les imatges arriben ja llestes a la pantalla i es dibuixen millor
        BufferStrategy estrategiaBufer = getBufferStrategy();
        if(estrategiaBufer == null)
        {
            createBufferStrategy(3);
            return;
        }
        pantalla.netejar();
        pantalla.mostrar(x,y);

        //copiem gràfics de la pantalla al joc
        //bucle for de la pnatalla
            //origen,posOrigen,desti,posDesti,tamanyArrayaCopiar
        System.arraycopy(pantalla.pixels,0, pixels,0,pixels.length);
        /*for(int i=0; i <pixels.length; i++)
        {
            pixels[i] = pantalla.pixels[i];
        }*/
        //dibuixem els gràfics copiats
        Graphics g = estrategiaBufer.getDrawGraphics(); //dibuixa les coses
        g.drawImage(imatge, 0, 0,getWidth(), getHeight(), null);
        //dibuixant el personatge temporal
        g.setColor(Color.CYAN);
        g.fill3DRect(AMPLADA/2, ALÇADA/2, 30, 30, true);
        //fí personatge temporal
        g.dispose(); //quan g acaba la reinicialitza
        estrategiaBufer.show();

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

        requestFocus(); //per saber on puc pica les tecles

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
