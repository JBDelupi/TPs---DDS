package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;

public interface ObserverHeladera {


    public void notify(Publicacion publicacion, Heladera heladera);
}
