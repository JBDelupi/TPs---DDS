package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter

public class SubscriptorHeladera implements ObserverHeladera{
    Colaborador colaborador;
    List<Publicacion> publicacionInteresadas;

    public SubscriptorHeladera(Colaborador colaborador){
        this.colaborador = colaborador;
        this.publicacionInteresadas = new ArrayList<>();
    }

    public void notify(Publicacion publicacion, Heladera heladera) {
        if (this.publicacionInteresadas.stream().anyMatch(f -> f.verificarCondicion(publicacion,heladera))) {
            colaborador.notify(publicacion);
        }

    }

    public void agregarPublicacion(Publicacion publicacion){
        publicacionInteresadas.add(publicacion);
    }


}
