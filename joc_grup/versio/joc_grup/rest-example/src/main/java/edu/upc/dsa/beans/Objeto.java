package edu.upc.dsa.beans;

import edu.upc.dsa.DAOG.DAO;
import edu.upc.dsa.beans.mapa.Drawable;

public class Objeto extends DAO implements Drawable {
    public String Nombre;
    public String Tipo;
    public String Descripcion;
    public int Valor;


    public Objeto() {

    }

    public Objeto(String nombre, String tipo, String descripcion, int valor) {
        Nombre = nombre;
        Tipo = tipo;
        Descripcion = descripcion;
        Valor = valor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int valor) {
        Valor = valor;
    }
}