package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import lombok.Setter;

@Setter
public class DonacionDeVianda extends FormaDeContribucion {
    private Vianda vianda;
    private Heladera heladera;


    @Override
    public Double generarPuntaje() {
        return 1 * 1.5;
    }
}