package edu.upc.dsa;

import edu.upc.dsa.DAOG.DAO;

import java.util.ArrayList;
import java.util.List;

public class Personatge extends DAO
{
    public String nombre;
    public int nivel;
    public int ataque;
    public int resistencia;
    List<Objeto> arrMisObjetos = new ArrayList<Objeto>();

    public void setArrMisObjetos(List<Objeto> arrMisObjetos) {
        this.arrMisObjetos = arrMisObjetos;
    }

    public List<Objeto> getArrMisObjetos() {
        return arrMisObjetos;
    }

    public int defensa;
    public Personatge(String nombre, int n, int a, int d, int r)//constructor
    {   this.nombre = nombre;
        this.nivel = n;
        this.ataque = a;
        this.resistencia = d;
        this.defensa = r;

    }
    public Personatge()//constructor
    {   this.nombre = null;
        this.nivel = 0;
        this.ataque = 0;
        this.resistencia = 0;
        this.defensa = 0;

    }

    public Objeto getPrimerObjetoLlamado(String nombreDeObjeto){
        for (Objeto miobjeto: arrMisObjetos)
        {
            if(miobjeto.Nombre.equals(nombreDeObjeto))
                return miobjeto;
        }
        return null;
    }
    public Objeto eliminarObjetoPorNombre(String nombreObjeto)
    {
        Objeto aborrar = null;
        for (Objeto miobjeto: arrMisObjetos)
        {
            if(miobjeto.Nombre.equals(nombreObjeto))
            {
                aborrar = miobjeto;
            }
        }
        if(aborrar == null){
            return null;
        }
        arrMisObjetos.remove(aborrar);
        return  aborrar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

}
