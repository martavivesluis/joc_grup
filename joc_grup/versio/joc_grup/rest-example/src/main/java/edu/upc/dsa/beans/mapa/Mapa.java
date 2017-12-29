package edu.upc.dsa.beans.mapa;
//un mapa es una col·leció de sprites

import edu.upc.dsa.beans.*;

import java.util.ArrayList;

public class Mapa {

    public ArrayList<Column> columns;


    //TODO: from file // OJO !!

    public Mapa(){

    }
    public ArrayList<Column> getColumns() {
        return columns;
    }
    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }
    public Mapa(int amplada, int alcada) {
        columns = new ArrayList<Column>();

        for(int i=0; i< amplada;i++){
            Column columna = new Column();
            for(int j = 0; j < alcada; j++){
                columna.rows.add(new EmptyCell());
            }
            columns.add(columna);
        }
    }
    public int doGetWidth() {
        return columns.size();
    }
    public int doGetHeight() {
        return columns.get(0).rows.size();
    }
    public void putElement(int x, int y, Drawable drawable1) {

        columns.get(x).rows.set(y,drawable1);
    }
    public Drawable doGetElement(int x, int y) {
        return columns.get(x).rows.get(y);
    }
    public void moure(int amuntInc, int esquerraInc, Drawable element) {

        int x = this.doGetDrawableIndexX(element);
        int y = this.doGetDrawableIndexY(element);
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
    private int doGetDrawableIndexY(Drawable element) {
        int x = this.doGetDrawableIndexX(element);
        return columns.get(x).rows.indexOf(element);
    }
    private int doGetDrawableIndexX(Drawable element) {
        for(int x = 0; x < this.doGetWidth(); x++){
            if(columns.get(x).rows.contains(element)){
                return x;
            }
        }
        return -1;
    }

    /****************************Mapas*******************/
    public Mapa miMapa(int nivel,Personatge mipersonaje){
        Mapa mimapa = this;
        Monstruo mimonstruo = new Monstruo();
        Objeto espasa = new Objeto("excalibur","espada","del rey arturo",10000);
        Objeto casco = new Objeto("helmuth","casco","del caballero verde",300);
        ParedCell pared = new ParedCell();
        try {
           espasa.insert();
           casco.insert();
           mimonstruo.select("id","1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch(nivel){
            case 1://nivell 1
                /*******Ho farem en un fitxer**********/
                mimapa.putElement(7,6,mipersonaje);
                mimapa.putElement(6,6,mimonstruo);
                mimapa.putElement(2,2,espasa);
                mimapa.putElement(3,3,casco);
                mimapa.putElement(5,4,pared);
                mimapa.putElement(4,4,pared);
                mimapa.putElement(3,4,pared);

                break;

        }

        return mimapa;
    }


}
    