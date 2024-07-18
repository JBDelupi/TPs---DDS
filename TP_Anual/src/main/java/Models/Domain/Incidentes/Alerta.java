package Models.Domain.Incidentes;

import Models.Domain.Heladera.EstadoHeladera;

public class Alerta extends Incidente {
    private TipoAlerta tipo;

    public Alerta(TipoAlerta tipo) {
        this.tipo = tipo;
        this.heladera.setEstadoActual(EstadoHeladera.NO_DISPONIBLE);
    }

    @Override
    public void notificar() {
        System.out.println("PELIGRO! Alerta de tipo: " + tipo);
    }
}
