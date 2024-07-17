package Models.Domain.Incidentes;

import Models.Domain.Heladera.Heladera;

import java.time.LocalDateTime;

public abstract class Incidente {
    private LocalDateTime fecha;
    private Heladera heladera;
    private Boolean solucionado;

    public void notificar(){}
}
