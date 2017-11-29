package edu.upc.dsa.mapa;
//un mapa es una col·leció de sprites

import edu.upc.dsa.Personatge;
import edu.upc.dsa.grafics.Pantalla;

import java.util.ArrayList;

public class Mapa {
    protected int[] requadres;
    ArrayList<ArrayList<Drawable>> cells;
    public Mapa(int amplada, int alcada) {
        cells = new ArrayList<ArrayList<Drawable>>();

        for(int i=0; i< amplada;i++){
            ArrayList<Drawable> columna = new ArrayList<Drawable>();
            for(int j = 0; j < alcada; j++){
                columna.add(new EmptyCell());
            }
            cells.add(columna);
        }
    }
    public int getWidth() {
        return cells.size();
    }
    public int getHeight() {
        return cells.get(0).size();
    }
    public void putElement(int x, int y, Drawable drawable1) {

        cells.get(x).set(y,drawable1);
    }
    public Drawable getElement(int x, int y) {
        return cells.get(x).get(y);
    }
    public void moure(int amuntInc, int esquerraInc, Drawable element) {

        int x = this.getDrawableIndexX(element);
        int y = this.getDrawableIndexY(element);
        if(amuntInc == 1 && esquerraInc == 0 ){
            this.putElement(x,y+1,element);
        }
        else if(amuntInc == -1 && esquerraInc == 0 ){
            this.putElement(x,y-1,element);
        }
        else if(amuntInc == 0 && esquerraInc == 1 ){
            this.putElement(x-1,y,element);
        }
        else if(amuntInc == 0 && esquerraInc == -1 ){
            this.putElement(x+1,y,element);
        }

        this.putElement(x,y,new EmptyCell());
    }
    private int getDrawableIndexY(Drawable element) {
        int x = this.getDrawableIndexX(element);
        return cells.get(x).indexOf(element);
    }
    private int getDrawableIndexX(Drawable element) {
        for(int x = 0; x < this.getWidth(); x++){
            if(cells.get(x).contains(element)){
                return x;
            }
        }
        return -1;
    }
    /*

    protected void generarMapa() {
    }

    private void Mapa(String ruta)
    {
        carregarMapa(ruta);
    }

    private void carregarMapa(String ruta) {
    }
    public void actualitzar()
    {

    }
    public void mostrar(int compensacioX, int compensacioY, Pantalla pantalla)
    {
        //investigar sobre bitShifting
        int adalt= compensacioY/32;
        int abaix= (compensacioY + pantalla.getAlcada())/32;
        int dreta = compensacioX / 32; //així només camina un pixel i no un quadre
        int esquerra = (compensacioX + pantalla.getAmplada())/32;
    }*/
}
    