package edu.upc.dsa.beans;

import edu.upc.dsa.DAOG.DAO;

import java.util.ArrayList;

public class Monstruo extends DAO{
    public String nomMonstruo;
    public int defensaMonstruo;
    public int atacMonstruo;
    public Objeto objetoMonstruo;

    //constructors
    public Monstruo(){
        this.nomMonstruo = null;
        this.defensaMonstruo = 1;
        this.atacMonstruo = 1;
        this.objetoMonstruo = new Objeto();
    }
    public Monstruo(String nomMonstruo, int defensaMonstruo, int atacMonstruo, Objeto objetoMonstruo){
        this.nomMonstruo = nomMonstruo;
        this.defensaMonstruo = defensaMonstruo;
        this.atacMonstruo = atacMonstruo;
        this.objetoMonstruo = objetoMonstruo;
    }

    public String getNomMonstruo() {
        return nomMonstruo;
    }

    public void setNomMonstruo(String nomMonstruo) {
        this.nomMonstruo = nomMonstruo;
    }

    public int getDefensaMonstruo() {
        return defensaMonstruo;
    }

    public void setDefensaMonstruo(int defensaMonstruo) {
        this.defensaMonstruo = defensaMonstruo;
    }

    public int getAtacMonstruo() {
        return atacMonstruo;
    }

    public void setAtacMonstruo(int atacMonstruo) {
        this.atacMonstruo = atacMonstruo;
    }

    public Objeto getObjetoMonstruo() {
        return objetoMonstruo;
    }

    public void setObjetoMonstruo(Objeto objetoMonstruo) {
        this.objetoMonstruo = objetoMonstruo;
    }



}
