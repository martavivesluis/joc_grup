import edu.upc.dsa.DAOG.DAO;

public class relacio extends DAO {
    int idJugador;
    int idPersonaje;

    public relacio()
    {}
    public relacio(int idJugador,int idPersonaje){
        this.idJugador = idJugador;
        this.idPersonaje = idPersonaje;
    }
    public int getIdJugador() {
        return idJugador;
    }
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
    public int getIdPersonaje() {
        return idPersonaje;
    }
    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }
}
