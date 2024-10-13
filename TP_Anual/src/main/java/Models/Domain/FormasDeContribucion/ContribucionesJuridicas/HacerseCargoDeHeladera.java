package Models.Domain.FormasDeContribucion.ContribucionesJuridicas;

import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import lombok.Setter;

@Setter
public class HacerseCargoDeHeladera extends Contribucion {
    private String nombreCaracteristico;
    private Heladera heladera;

    public HacerseCargoDeHeladera() {
        this.nombre = "Hacerse cargo de heladera";
    }
}
