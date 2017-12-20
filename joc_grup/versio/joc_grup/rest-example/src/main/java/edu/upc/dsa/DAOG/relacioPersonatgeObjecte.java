package edu.upc.dsa.DAOG;

public class relacioPersonatgeObjecte extends DAO {
    public int idObjecte;
    public int idPersonatge;



    public relacioPersonatgeObjecte() {
    }

    public relacioPersonatgeObjecte(int idObjecte, int idPersonatge) {
        this.idObjecte = idObjecte;
        this.idPersonatge = idPersonatge;
        this.hasId = false;
    }


    public int getIdObjecte() {
        return idObjecte;
    }

    public void setIdObjecte(int idObjecte) {
        this.idObjecte = idObjecte;
    }

    public int getIdPersonatge() {
        return idPersonatge;
    }

    public void setIdPersonatge(int idPersonatge) {
        this.idPersonatge = idPersonatge;
    }
}

