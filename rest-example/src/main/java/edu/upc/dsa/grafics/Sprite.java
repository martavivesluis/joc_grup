package edu.upc.dsa.grafics;

public final class Sprite {
    //primer volem saber el tamany del sprite tamany= lado de cada quadrat del full de sprites
    private final int tamany;
    //coordenades dels pixels
    private int x;
    private int y;
    //array que guarda col·leció de colors
    public int[]pixels;
    private final FullSprites fullSprites;

    //col·leció sprites
    public static Sprite asfalto = new Sprite(32,0,0, FullSprites.panda);
    //fí de la col·leció

    public Sprite(int tamany, final int columna, final int fila, final FullSprites full) {
        this.tamany = tamany;

        pixels = new int[this.tamany*this.tamany];
        this.x=columna*tamany;
        this.y=fila*tamany;
        this.fullSprites=full;

        //extreure sprite de full de sprites
        for(int y=0; y<tamany;y++)
        {
            for(int x=0; x<tamany;x++)
            {
                //array que representa el nostre sprite
                pixels[(x+y)*tamany]=full.pixels[((x + this.x)+ (y + this.y))*full.getAmplada()];
            }
        }
    }

}
