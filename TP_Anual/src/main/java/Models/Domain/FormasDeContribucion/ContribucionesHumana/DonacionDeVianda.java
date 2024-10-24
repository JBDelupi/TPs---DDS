package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import jakarta.persistence.Transient;
import lombok.Setter;

@Setter
public class DonacionDeVianda extends Contribucion {
    @Transient
    private Vianda vianda;

    @Transient
    private Heladera heladera;

    @Override
    public Double generarPuntaje() {
        return 1 * 1.5;
    }

    public DonacionDeVianda(){
        this.nombre = "Donacion de vianda";
    }

}