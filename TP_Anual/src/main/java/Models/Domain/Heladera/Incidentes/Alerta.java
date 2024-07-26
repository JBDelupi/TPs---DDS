package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.EstadoHeladera;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import lombok.Getter;

@Getter
public class Alerta extends Incidente {
    private TipoAlerta tipo;

    public Alerta(TipoAlerta tipo, Heladera heladera) {
        this.tipo = tipo;
        this.heladera = heladera;
    }

}
