package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import Models.Domain.Personas.Actores.Tecnico;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Alerta extends Incidente {
    private Integer id;
    private TipoAlerta tipo;

    public Alerta(TipoAlerta tipo, Heladera heladera) {
        this.tipo = tipo;
        this.heladera = heladera;
        this.solucionado = false;
    }



}
