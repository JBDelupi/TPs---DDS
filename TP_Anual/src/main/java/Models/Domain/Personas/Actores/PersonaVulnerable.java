package Models.Domain.Personas.Actores;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PersonaVulnerable extends Rol {

    private LocalDate fechaRegistro;
    private Boolean flagSituacionDeCalle;
    private Integer menoresACargo;


    public Boolean tieneMenoresACargo(){
        return menoresACargo>0;
    }
}
