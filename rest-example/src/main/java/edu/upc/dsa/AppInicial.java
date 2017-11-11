package edu.upc.dsa;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AppInicial {

    public void Arrencar()

    {
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
            try {
                switch (opcio) {
                    case 1:
                        System.out.println("Has triat: 1");
                        break;
                    case 2:
                        System.out.println("Has triat: 12");
                        break;
                    case 3:
                        System.out.println("Has triat: 13");
                        break;
                    case 4:
                        System.out.println("Fins la propera");
                        sortida = true;
                        break;
                    default:
                        System.out.println("Solament pots escollir entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }

        }
    }

}


