package Models.Domain.Personas.Actores;

import Models.Domain.Tarjetas.TarjetaAlimentar;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PersonaVulnerable extends Rol {

    private LocalDate fechaRegistro;
    private Boolean flagSituacionDeCalle;
    private Integer menoresACargo;
    private TarjetaAlimentar tarjeta;

    public Boolean tieneMenoresACargo(){
        return menoresACargo>0;
    }
}
