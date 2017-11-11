package edu.upc.dsa;
import java.util.ArrayList;
import java.util.Scanner;


public class AppInicial {
    SingletonMundo instanciaunica = SingletonMundo.getInstance();

    Scanner sn = new Scanner(System.in);
    int opcio;
    boolean sortida = false;
    while (!sortida)

    {
        System.out.println("Escull una de les opcions");
        System.out.println("-------------------------");
        System.out.println("1. Loggin");
        System.out.println("2. Registar-se");
        System.out.println("3. Instruccions Joc");
        System.out.println("4. Sortir");


        opcio = sn.nextInt();

    }


}


