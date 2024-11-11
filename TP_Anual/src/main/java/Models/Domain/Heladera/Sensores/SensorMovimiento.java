package Models.Domain.Heladera.Sensores;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Incidentes.Incidente;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


public class SensorMovimiento implements Sensor {

    private Heladera heladera;

    private Boolean estaPrendido;

    public SensorMovimiento(Heladera heladera) {
        this.heladera = heladera;
        this.estaPrendido = false;
    }

    public void chequear(){
        if(this.estaCerradaHeladera() && this.estaPrendido){
            heladera.generarIncidente(TipoAlerta.FRAUDE);
        }
    }

    @Override
    public void activar() {
        this.estaPrendido = true;
    }

    @Override
    public void desactivar() {
        this.estaPrendido = false;
    }


    public boolean estaCerradaHeladera(){
        return !this.heladera.getAbierto();
    }

}

