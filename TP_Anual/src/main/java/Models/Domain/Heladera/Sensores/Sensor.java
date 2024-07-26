package Models.Domain.Heladera.Sensores;

import Models.Domain.Heladera.Incidentes.Incidente;

public interface Sensor {

    public void activar();
    public void desactivar();
    public void chequear();

}
