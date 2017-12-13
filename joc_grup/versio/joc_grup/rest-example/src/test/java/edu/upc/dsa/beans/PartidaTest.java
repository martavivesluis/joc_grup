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
            Partida mipartida = new Partida(200,300,dada);
            mipartida.insert();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
