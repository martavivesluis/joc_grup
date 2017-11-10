package edu.upc.dsa;

import java.util.ArrayList;
import java.util.List;

public class Usuario extends DAO
{
    public String nombre;
    public String password;
    public int nivel;
    public int ataque;
    public int resistencia;
    public int defensa;
    //List<Objeto> MisObjetos = new ArrayList<Objeto>();
    public Usuario(String nombre,String c,int n,int a, int d, int r)//constructor
    {   this.nombre = nombre;
        this.password = c;
        this.nivel = n;
        this.ataque = a;
        this.resistencia = d;
        this.defensa = r;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
