package Models.Domain.FormasDeContribucion.ContribucionesJuridicas;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Setter;

@Setter

@Entity
@DiscriminatorValue("hacerse_cargo_de_heladera")
public class HacerseCargoDeHeladera extends Contribucion {
    @Column(name = "nombre_caracteristico")
    private String nombreCaracteristico;

    @Transient
    private Heladera heladera;

    public HacerseCargoDeHeladera() {
        this.nombre = "Hacerse cargo de heladera";
    }
}
