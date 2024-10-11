package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Persona;

public interface ObserverHeladera {

     void update(TipoDePublicacion tipoDePublicacion,Heladera heladera);

     public Persona getColaborador();
}
