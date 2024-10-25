package Models.Domain.Personas.Actores;

import Models.Domain.Tarjetas.TarjetaAlimentar;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

@Entity
@DiscriminatorValue("persona_vulnerable")
public class PersonaVulnerable extends Rol {
    @Temporal(TemporalType.DATE)
    private LocalDate fechaRegistro;

    @Column(name = "flag_situacion_de_calle")
    private Boolean flagSituacionDeCalle;

    @Column(name = "menores_a_cargo")
    private Integer menoresACargo;

    @OneToOne(cascade = CascadeType.PERSIST)
    private TarjetaAlimentar tarjeta;

    public PersonaVulnerable(){
        this.tipo = TipoRol.VULNERABLE;
    }

    public Boolean tieneMenoresACargo(){
        return menoresACargo>0;
    }
}
