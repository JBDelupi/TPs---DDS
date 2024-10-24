package Models.Domain.Personas.DatosPersonales;

import Service.APIPuntos.Punto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Embeddable
public class Direccion {

    @Column(name = "localidad")
    private String Localidad;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private String numero;

    @Embedded
    private Punto centro;
}
