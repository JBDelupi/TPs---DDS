package Models.Domain.Personas.Actores;

import Models.Domain.DatosPersonales.Direccion;
import Models.Domain.DatosPersonales.TipoDeDocumento;
import Models.Domain.Personas.Utilidades.TipoRolNegocio;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PersonaVulnerable extends Persona {

    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaRegistro;
    private Boolean flagSituacionDeCalle;
    private Direccion domicilio;
    private TipoDeDocumento tipoDeDocumento;
    private String numeroDocumento;
    private Integer menoresACargo;

    public  PersonaVulnerable (){
        this.setRolNegocio(TipoRolNegocio.VULNERABLE);
    }


    public Boolean tieneMenoresACargo(){
        return menoresACargo>0;
    }
}
