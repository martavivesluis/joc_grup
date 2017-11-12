package edu.upc.dsa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Usuario extends DAO
{
    public String nombre;
    public int id;
    public int nivel;
    public int ataque;
    public int resistencia;
    //List<Objeto> MisObjetos = new ArrayList<Objeto>();
    public int defensa;
    private static final AtomicInteger count = new AtomicInteger(0);
    public Usuario(String nombre,String c,int n,int a, int d, int r)//constructor
    {   this.nombre = nombre;
        this.nivel = n;
        this.ataque = a;
        this.resistencia = d;
        this.defensa = r;

    }
    public Usuario()//constructor
    {   this.nombre = null;
        this.nivel = 0;
        this.ataque = 0;
        this.resistencia = 0;
        this.defensa = 0;
        this.id = count.incrementAndGet();

    }
    /*
    public Objeto getPrimerObjetoLlamado(String nombreDeObjeto){
        for (Objeto miobjeto:MisObjetos)
        {
            if(miobjeto.Nombre.equals(nombreDeObjeto))
                return miobjeto;
        }
        return null;
    }
    public Objeto eliminarObjetoPorNombre(String nombreObjeto)
    {
        Objeto aborrar = null;
        for (Objeto miobjeto:MisObjetos)
        {
            if(miobjeto.Nombre.equals(nombreObjeto))
            {
                aborrar = miobjeto;
            }
        }
        if(aborrar == null){
            return null;
        }
        MisObjetos.remove(aborrar);
        return  aborrar;
    }*/

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
