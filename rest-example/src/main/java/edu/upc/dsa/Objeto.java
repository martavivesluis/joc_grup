package edu.upc.dsa;

public class Objeto{
   /* public String Nombre;
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

    }*/

     //CODIGO PARA LA API REST



    private String nombre;
    private String tipo;
    private String calidad;
    private int durabilidad;


    public Objeto(){

    }

    public Objeto(String nombre, String id, String calidad, int durabilidad) {
        this.nombre = nombre;
        this.tipo = id;
        this.calidad = calidad;
        this.durabilidad = durabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public int getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(int durabilidad) {
        this.durabilidad = durabilidad;
    }
}


