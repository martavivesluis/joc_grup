package edu.upc.dsa;

public class Objeto{
    public String Nombre;
    private String Tipo;
    private String Descripcion;
    private int Valor;
    private int  Coste;

    public Objeto(String nombre,String t,String d,int v, int c)//constructor
    {   this.Nombre = nombre;
        this.Tipo = t;
        this.Descripcion = d;
        this.Valor = v;
        this.Coste = c;

    }


}


