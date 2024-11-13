package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@DiscriminatorValue("distribucion_de_viandas")
public class DistribucionDeViandas extends Contribucion {
   
    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "heladera_origen_id")
    private Heladera heladeraOrigen;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "heladera_destino_id") // FK
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
