package Models.Domain.Incidentes;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public abstract class Incidente {
    protected LocalDateTime fecha;
    protected Heladera heladera;
    protected Boolean solucionado;

    public Incidente() {

    }


    public void notificar(){}
}
