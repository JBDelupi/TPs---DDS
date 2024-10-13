package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import lombok.Setter;

@Setter
public class DistribucionDeViandas extends Contribucion {
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandasAMover;
    private String motivo;

    @Override
    public Double generarPuntaje() {
        return (double)cantidadDeViandasAMover * 1;
    }

    public DistribucionDeViandas(){
        this.nombre = "Distribucion de viandas";
    }

}
