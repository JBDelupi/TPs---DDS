package Models.Domain.Tarjetas;

import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Heladera.Heladera;

import java.time.LocalDateTime;

public class SolicitudDeApertura {
    private FormaDeContribucion accion;
    private LocalDateTime fechaApertura;
    private Heladera heladera;
    private LocalDateTime fechaLimite;
    private Double horaLimite;
    private Boolean realizada;

    public SolicitudDeApertura(FormaDeContribucion accion, Heladera heladera) {
        fechaApertura = LocalDateTime.now();
        realizada = Boolean.FALSE;
        this.accion = accion;
        fechaLimite = fechaApertura.plusMinutes((long)(horaLimite*60));
    }
}
