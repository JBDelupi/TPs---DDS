package Models.Domain.Tarjeta;


import Models.Domain.Heladera;
import Models.Domain.Vianda;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

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
