package Models.Domain.FormasDeContribucion;

import Models.Domain.Heladera;
import lombok.Setter;

import java.time.LocalDate;
@Setter
public class DistribucionDeViandas extends FormaDeContribucion{
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
