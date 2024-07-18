package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;
import lombok.Getter;

@Getter
public abstract class Publicacion {
    protected String nombre;
    protected String descripcion;
    protected TipoPublicacion tipoPublicacion;

    protected Boolean verificarCondicion(Heladera heladera) {
        return false;
    }
}
