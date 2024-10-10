package Models.Domain.Tarjetas;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegistroDeUso {
    private LocalDate fecha;
    private Heladera heladera;
    private Vianda vianda;
    private TipoAccion accion;

    public RegistroDeUso(Heladera heladera, Vianda vianda, TipoAccion accion){
        this.heladera = heladera;
        this.vianda = vianda;
        this.fecha = LocalDate.now();
        this.accion = accion;
    }





}
