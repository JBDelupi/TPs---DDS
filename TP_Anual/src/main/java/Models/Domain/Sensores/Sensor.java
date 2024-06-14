package Models.Domain.Sensores;

import Models.Domain.Heladera;
import lombok.Setter;

public interface Sensor {

    public void activar();
    public void desactivar();

    public void notificar();

}
