package edu.upc.dsa.beans.mapa;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("pared")
public class ParedCell implements Drawable {
    public ParedCell(){

    }
    @Override
    public int dogetId() {
        return 1;
    }

    @Override
    public String dogetTipus() {
        return "ParedCell";
    }
}
