package Models.FormasDeContribucion;

import Models.Colaborador;
import Models.Heladera;

import java.time.LocalDate;

public class DistribucionDeViandas extends FormaDeContribucion{
    private Heladera heladeraOrigen;
    private Heladera heladeraDestino;
    private Integer cantidadDeViandasAMover;
    private String motivo;
    private LocalDate fechaRealizada;

    @Override
    public void generarDonacion(Colaborador humano) {

    }
}
