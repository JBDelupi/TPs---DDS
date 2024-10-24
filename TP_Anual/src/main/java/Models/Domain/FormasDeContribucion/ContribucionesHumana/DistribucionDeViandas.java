package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Setter;

@Setter

@Entity
@DiscriminatorValue("distribucion_de_viandas")
public class DistribucionDeViandas extends Contribucion {
    @Transient
    private Heladera heladeraOrigen;

    @Transient
    private Heladera heladeraDestino;

    @Column(name = "cantidad_de_viandas_a_mover")
    private Integer cantidadDeViandasAMover;

    @Column(name = "motivo")
    private String motivo;

    @Override
    public Double generarPuntaje() {
        return (double)cantidadDeViandasAMover * 1;
    }

    public DistribucionDeViandas(){
        this.nombre = "Distribucion de viandas";
    }

}
