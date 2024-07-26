package Models.Domain.FormasDeContribucion.ContribucionesJuridicas;

import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Heladera.Heladera;
import lombok.Setter;

@Setter
public class HacerseCargoDeHeladera extends FormaDeContribucion {
    private String nombreCaracteristico;
    private Heladera heladera;


}
