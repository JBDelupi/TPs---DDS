package Models.Domain.DatosPersonales;

import Service.APIPuntos.Punto;
import lombok.Getter;

@Getter

public class Direccion {
    private String Localidad;
    private String calle;
    private String numero;
    private Punto centro;
}
