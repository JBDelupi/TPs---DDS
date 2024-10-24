package Models.Domain.Heladera.Incidentes;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import Models.Domain.Personas.Actores.Tecnico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@DiscriminatorValue("Alerta")
@NoArgsConstructor

public class Alerta extends Incidente {
   @Enumerated(EnumType.STRING)
   @Column(name = "tipo_alerta")
   private TipoAlerta tipo;

   public Alerta(TipoAlerta tipo, Heladera heladera) {
        this.tipo = tipo;
        this.heladera = heladera;
        this.solucionado = false;
   }

}
