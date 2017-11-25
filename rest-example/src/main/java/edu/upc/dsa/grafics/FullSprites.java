package edu.upc.dsa.grafics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FullSprites {

    private final int amplada;
    private final int alcada;
    public final int[]pixels;

    //Col·leció de fulls de sprites
        //creació nou objecte


    public static FullSprites gris = new FullSprites("/recursos/gris.jpg/", 320, 320);


    //fí de la col·leció

    public FullSprites(final String ruta, final int amplada, final int alcada)
    {
        this.amplada=amplada;
        this.alcada=alcada;

        //array té el mateix tamany que pixels que tingui la nostra imatge
        pixels = new int[amplada*alcada];

        //ara hem de posar els colors de la imatge
        BufferedImage imatge = null;
        try {
            imatge = ImageIO.read(FullSprites.class.getResource(ruta));
            imatge.getRGB(0,0, amplada, alcada, pixels, 0, amplada);
            //aqui haig de posar un log4java per dir si he agafat o no la imatge
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public int getAlcada() {
        return alcada;
    }

    public int getAmplada() {
        return amplada;
    }

}
