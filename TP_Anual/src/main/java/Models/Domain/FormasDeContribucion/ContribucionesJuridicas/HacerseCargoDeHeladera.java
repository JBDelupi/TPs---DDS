package Models.Domain.FormasDeContribucion.ContribucionesJuridicas;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

@Entity
@DiscriminatorValue("hacerse_cargo_de_heladera")
public class HacerseCargoDeHeladera extends Contribucion {

    @Column(name = "nombre_caracteristico")
    private String nombreCaracteristico;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id", name = "heladera_id")
    private Heladera heladera;

    public HacerseCargoDeHeladera() {
        this.nombre = "Hacerse cargo de heladera";
    }
}
