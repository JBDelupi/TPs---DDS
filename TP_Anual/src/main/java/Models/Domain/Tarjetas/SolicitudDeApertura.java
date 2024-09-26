package Models.Domain.Tarjetas;

import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter @Setter
public class SolicitudDeApertura {
    private TipoDonacion accion;
    private LocalDateTime fechaApertura;
    private Heladera heladera;
    private LocalDateTime fechaLimite;
    private Double horaLimite;
    private Boolean realizada;

    public SolicitudDeApertura(TipoDonacion accion, Heladera heladera) {
        fechaApertura = LocalDateTime.now();
        realizada = Boolean.FALSE;
        this.accion = accion;
        this.horaLimite = 1.00;
        fechaLimite = fechaApertura.plusMinutes((long)(horaLimite*60));
    }
}
