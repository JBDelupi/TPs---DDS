package Models.Domain.FormasDeContribucion;

import Models.Domain.Heladera;

import java.time.LocalDate;

public class DistribucionDeViandas extends FormaDeContribucion{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandasAMover;
    private String motivo;

    public DistribucionDeViandas(Heladera heladeraOrigen, Heladera heladeraDestino, Integer cantidadDeViandasAMover, String motivo) {
        this.heladeraOrigen = heladeraOrigen;
        this.heladeraDestino = heladeraDestino;
        this.cantidadDeViandasAMover = cantidadDeViandasAMover;
        this.motivo = motivo;
        this.fechaDeDonacion = LocalDate.now();
    }
    public DistribucionDeViandas(){
    }


    @Override
    public Double generarPuntaje() {
        return (double)cantidadDeViandasAMover * 1;
    }
}
