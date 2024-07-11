package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Heladera.Heladera;
import lombok.Setter;

@Setter
public class DistribucionDeViandas extends FormaDeContribucion {
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandasAMover;
    private String motivo;

    public DistribucionDeViandas(){

    }


    @Override
    public Double generarPuntaje() {
        return (double)cantidadDeViandasAMover * 1;
    }
}
