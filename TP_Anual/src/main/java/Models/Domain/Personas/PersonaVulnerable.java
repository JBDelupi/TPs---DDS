package Models.Domain.Personas;

import Models.Domain.Direccion;
import Models.Domain.TipoDeDocumento;
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
        this.nombre = nombre;
        this.menoresACargo = menoresACargo;
        this.setRolNegocio(TipoRolNegocio.VULNERABLE);
    }


    public Boolean tieneMenoresACargo(){
        return menoresACargo>0;
    }
}
