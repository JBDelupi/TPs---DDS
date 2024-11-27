package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import Models.Repository.RepoContribucion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String getDetalle() {
        String unDetalle = " ";
        unDetalle += "Heladera Origen: " + this.getHeladeraOrigen().getId();
        unDetalle += ", Heladera Destino: " + this.getHeladeraDestino().getId();
        unDetalle += ", Cantidad: " + this.getCantidadDeViandasAMover().toString();
        unDetalle += ", Fecha de Donación: " + this.getFechaDeDonacion().toString();
        return unDetalle;
    }



}
