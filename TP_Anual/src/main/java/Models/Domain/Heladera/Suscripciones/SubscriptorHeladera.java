package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;

import java.io.Serializable;
import java.util.List;

public class SubscriptorHeladera implements ObserverHeladera{
    Colaborador colaborador;
    List<Publicacion> publicacionInteresadas;


    public void notify(Publicacion publicacion, Heladera heladera) {
        if (this.publicacionInteresadas.stream().anyMatch(f -> f.verificarCondicion(heladera))) {
            colaborador.notify(publicacion);
        }

    }

    public void agregarPublicacion(Publicacion publicacion){
        publicacionInteresadas.add(publicacion);
    }


}
