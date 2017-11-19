package edu.upc.dsa.mapa;
//un mapa es una col·leció de sprites

import edu.upc.dsa.grafics.Pantalla;

public abstract class Mapa {
    protected int amplada;
    protected int alcada;
    
    protected int[] requadres;
    
    public Mapa(int amplada, int alcada)
    {
        this.amplada=amplada;
        this.alcada=alcada;
        
        requadres = new int[amplada*alcada];
        generarMapa();
        
    }

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
    public void mostrar(int compesacioX, int compensacioY, Pantalla pantalla)
    {

    }
}
    