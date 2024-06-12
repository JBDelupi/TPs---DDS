package Models.Domain.FormasDeContribucion;

import Models.Domain.Heladera;
import Models.Domain.Vianda;
import lombok.Setter;

import java.time.LocalDate;

@Setter
public class DonacionDeVianda extends FormaDeContribucion{
    private Vianda vianda;
    private Heladera heladera;

    public DonacionDeVianda(Vianda vianda, Heladera heladera){
        heladera.agregarVianda(vianda);
        this.vianda = vianda;
        this.heladera = heladera;
        this.fechaDeDonacion = LocalDate.now();
    }

    public DonacionDeVianda(){
    }

    @Override
    public Double generarPuntaje() {
        return 1 * 1.5;
    }
}