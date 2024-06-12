package Models.Domain.FormasDeContribucion;

import Models.Domain.Heladera;
import Models.Domain.Vianda;
import lombok.Setter;

import java.time.LocalDate;

@Setter
public class DonacionDeVianda extends FormaDeContribucion{
    private Vianda vianda;
    private Heladera heladera;

    public DonacionDeVianda(){
    }

    @Override
    public Double generarPuntaje() {
        return 1 * 1.5;
    }
}