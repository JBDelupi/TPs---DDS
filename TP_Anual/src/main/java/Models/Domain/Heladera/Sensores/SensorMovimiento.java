package Models.Domain.Heladera.Sensores;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Incidentes.Incidente;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;

public class SensorMovimiento implements Sensor {
    private Heladera heladera;
    private Boolean estaPrendido;

    public SensorMovimiento(Heladera heladera) {
        this.heladera = heladera;
        this.estaPrendido = false;
    }

    public void chequear(){
        if(this.estaCerradaHeladera() && this.estaPrendido){
            Alerta incidente = new Alerta(TipoAlerta.FRAUDE, heladera);

            heladera.notify(incidente);
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

