package Models.Personas;

import Models.Direccion;
import Models.TipoDeDocumento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PersonaVulnerable{

    private String nombre;
    private LocalDate fechaDeNacimiento;
    private LocalDate fechaRegistro;
    private Boolean flagSituacionDeCalle;
    private Direccion domicilio;
    private TipoDeDocumento tipoDeDocumento;
    private String numeroDocumento;
    private Integer menoresACargo;

    public  PersonaVulnerable (String nombre, Integer menoresACargo){
        this.nombre = nombre;
        this.menoresACargo = menoresACargo;
    }


    public Boolean tieneMenoresACargo(){
        return menoresACargo>0;
    }
}
