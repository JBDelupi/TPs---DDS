package Models.FormasDeContribucion;

import Models.Heladera;
import Models.Vianda;
import lombok.Setter;

import java.time.LocalDate;

@Setter
public class DonacionDeVianda extends FormaDeContribucion{
    private Vianda vianda;
    private LocalDate fechaDonacion;
    private Heladera heladera;

    public DonacionDeVianda(Vianda vianda, Heladera heladera){
        heladera.agregarVianda(vianda);
        this.vianda = vianda;
        this.heladera = heladera;
    }


    @Override
    public Double generarPuntaje() {
        return 1 * 1.5;
    }
}