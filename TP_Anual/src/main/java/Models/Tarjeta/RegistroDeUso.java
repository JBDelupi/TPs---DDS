package Models.Tarjeta;

import Models.Heladera;

import java.time.LocalDate;

public class RegistroDeUso {

    public RegistroDeUso(Heladera heladera){
        this.heladera = heladera;
        this.fecha = LocalDate.now();
    }
    private LocalDate fecha;
    private Heladera heladera;


}
