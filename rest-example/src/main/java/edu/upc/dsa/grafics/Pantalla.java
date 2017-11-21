package edu.upc.dsa.grafics;

public final class Pantalla {
    private final int alcada;
    private final int amplada;

    public final int[] pixels;

        //temporal
        private final static int COSTATSPRITE = 32;
        private final static int MASCARA_SPRITE= COSTATSPRITE -1;
        //fí temporal

    public Pantalla(final int amplada, final int alcada)
    {
        this.alcada = alcada;
        this.amplada = amplada;
        pixels = new int[amplada*alcada];

    }
    public void netejar()//per esborrar si es mou el gràfic
    {
        for(int i = 0; i<pixels.length; i++)
        {
            pixels[i]=0; //negre absolut

        }
    }
    private int limitador(int valornou,int valorminim,int valormaxim)
    {
        if(valornou > valormaxim)
        {
            return valormaxim;
        }
        if(valornou< valorminim)
        {
            return valorminim;
        }
        return valornou;
    }

    public void mostrar(final int compensacioX, final int compensacioY)
    {
        for(int y=0; y<alcada; y++)
        {
            int posicioY = y + compensacioY;
            if(posicioY<0||posicioY>=alcada) //control no sortir del mapa
            {
                continue;//ens sortim del bucle, i ens saltem el bucle de la x
            }

            for(int x=0; x<amplada; x++)
            {
                int posicioX = x + compensacioX;
                if(posicioX<0||posicioY>=amplada) //control no sortir del mapa
                {
                    continue;
                }
                //si lo de abans es compleix redibuixar pantalla
                //El costat del sprite indica tamany sprite = 32pixels
                //x & MASCARA_SPRITE + cada cegada que x traspasi el valor torni a ser 0
                //temporal
                pixels[(posicioX+ posicioY)*amplada] =
                        Sprite.asfalto.pixels[((x & MASCARA_SPRITE )+ (y & MASCARA_SPRITE))*COSTATSPRITE];
            }
        }
    }

}
