package Models.Domain.FormasDeContribucion.ContribucionesHumana;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Vianda;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter

@Entity
@DiscriminatorValue("donacion_de_vianda")
public class DonacionDeVianda extends Contribucion {

    @OneToOne()
    private Vianda vianda;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "heladera_id")
    private Heladera heladera;

    @Override
    public Double generarPuntaje() {
        return 1 * 1.5;
    }

    public DonacionDeVianda(){
        this.nombre = "Donacion de vianda";
    }

    public String getDetalle() {
        String unDetalle = "";
        unDetalle += "Vianda: " + (this.getVianda() != null && this.getVianda().getNombre() != null ? this.getVianda().getNombre() : "null");
        unDetalle += ", Heladera: " + (this.getHeladera() != null && this.getHeladera().getId() != null ? this.getHeladera().getId() : "null");
        return unDetalle;
    }






}