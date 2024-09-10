package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.Heladera;

import Models.Domain.Personas.Actores.Tecnico;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;
import Service.SistemaDeGeolocalizacion.SistemaGeolocalizacion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public abstract class Incidente {
    protected LocalDateTime fecha;
    protected Heladera heladera;
    protected Boolean solucionado;


    public Mensaje generarMensaje(String destinatario, String asunto, String contenido){
        MensajeBuilder builder = new MensajeBuilder();
        return builder
                .asunto(asunto)
                .contenido(contenido)
                .destinatario(destinatario)
                .construir();
    }



}
