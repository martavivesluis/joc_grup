package edu.upc.dsa;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AppInicial {

    public void Arrencar()

    {
        SingletonMundo instanciaunica = SingletonMundo.getInstance();

        Scanner sn = new Scanner(System.in);
        int opcio, aux;
        String auxString;
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
                            System.out.println("Introduexi el seu nom d'usuari");
                            auxString = sn.next();
                            if(auxString.equals(instanciaunica.mundo.consultarNomJugador(auxString)))
                            {
                                System.out.println("Usuari Correcte");
                            }
                            else
                                System.out.println("Usuari Incorrecte");

                            System.out.println("Introduexi la seva contrassenya");
                            auxString = sn.next();
                            if(auxString.equals(instanciaunica.mundo.consultarContraJugador(auxString)))
                            {
                                System.out.println("Contrasenya Correcta");
                            }
                            else
                                System.out.println("Contrasenya Incorrecta");
                        break;
                    case 2:
                            System.out.println("Has triat: 2");
                            System.out.println("Primer has de escollir un personatge inicial");
                            System.out.println("1.Bruixa, 2.Gnomo, 3.Fantasma ");
                            Usuario personantge = new Usuario();
                            aux = sn.nextInt();
                            switch (aux)
                            {
                                case 1:
                                    personantge.setNombre("Bruixa");
                                    break;
                                case 2:
                                    personantge.setNombre("Gnomo");
                                    break;
                                case 3:
                                    personantge.setNombre("Fantasma");
                                    break;
                                default:
                                    System.out.println("Solament pots escollir entre 1 i 3");
                            }
                                instanciaunica.mundo.crearUsuario(personantge);
                                Jugador nouJugador = new Jugador("Anna", "marianet", "marianet9990", personantge);
                                instanciaunica.mundo.crearJugador(nouJugador);
                        break;
                    case 3:
                        System.out.println("Has triat: 3");
                        break;
                    case 4:
                        System.out.println("Fins la propera");
                        sortida = true;
                        break;
                    default:
                        System.out.println("Solament pots escollir entre 1 i 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }

        }
    }

}


