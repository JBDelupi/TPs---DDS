package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;

public class PublicacionNViandasDisponibles extends Publicacion {
    private int cantidadDisponible;

    public PublicacionNViandasDisponibles(int cant) {
        this.cantidadDisponible = cant;
    }

    public Boolean verificarCondicion(Heladera heladera){
        return  tipoPublicacion == TipoPublicacion.N_VIANDAS_DISPONIBLES && cantidadDisponible  == heladera.getCapacidadDeViandas();
    }
}
