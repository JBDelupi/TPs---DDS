package Models.FormasDeContribucion;

import Models.Personas.Colaborador;
import Models.Heladera;

import java.time.LocalDate;

public class DistribucionDeViandas extends FormaDeContribucion{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandasAMover;
    private String motivo;
    private LocalDate fechaRealizada;

    public DistribucionDeViandas(Heladera heladeraOrigen, Heladera heladeraDestino, Integer cantidadDeViandasAMover, String motivo) {
        this.heladeraOrigen = new Heladera();
        this.heladeraDestino = new Heladera();
        this.cantidadDeViandasAMover = 0;
        this.motivo = "";
        this.fechaRealizada = LocalDate.now();
    }


    @Override
    public Double generarPuntaje() {
        return cantidadDeViandasAMover.doubleValue() * 1;
    }
}
