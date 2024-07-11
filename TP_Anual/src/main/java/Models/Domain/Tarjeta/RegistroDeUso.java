package Models.Domain.Tarjeta;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RegistroDeUso {

    public RegistroDeUso(Heladera heladera, Vianda vianda){
        this.heladera = heladera;
        this.vianda = vianda;
        this.fecha = LocalDate.now();
    }
    private LocalDate fecha;
    private Heladera heladera;
    private Vianda vianda;

}
