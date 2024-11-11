package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Persona;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_subscripcion")
public abstract class ObserverHeladera {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     abstract public void update(TipoDePublicacion tipoDePublicacion, Heladera heladera);

     abstract public Persona getColaborador();

}
