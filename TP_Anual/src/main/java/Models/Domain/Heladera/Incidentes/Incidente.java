package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.Heladera;

import Service.Notificacion.Mensaje.MensajeBienvenida;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class Incidente {
    protected LocalDateTime fecha;
    protected Heladera heladera;
    protected Boolean solucionado;


}
