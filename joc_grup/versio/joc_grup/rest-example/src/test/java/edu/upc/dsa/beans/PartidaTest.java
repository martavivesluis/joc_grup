package edu.upc.dsa.beans;

import edu.upc.dsa.beans.Partida;
import junit.framework.TestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class PartidaTest extends TestCase {
    public void testIdEsDiferent0()throws Exception
    {   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date ahora = new Date();
            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
            String dada = formateador.format(ahora);
            //t'afegeix les partides a la base de dades a partir de la hora actual
            Partida mipartida = new Partida(200,800,dada);
            Partida mipartida2 = new Partida(200,600,dada);
            Partida mipartida3 = new Partida(200,700,dada);
            Partida mipartida4 = new Partida(200,900,dada);
            Partida mipartida5 = new Partida(200,900,dada);
            Partida mipartida6 = new Partida(200,400,dada);
            mipartida.insert();mipartida2.insert();mipartida3.insert();mipartida4.insert();mipartida5.insert();mipartida6.insert();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
