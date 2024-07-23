package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Publicacion {
    protected String nombre;
    protected String descripcion;
    protected TipoPublicacion tipoPublicacion;

    protected abstract Boolean verificarCondicion(Publicacion publicacion, Heladera heladera);
}
