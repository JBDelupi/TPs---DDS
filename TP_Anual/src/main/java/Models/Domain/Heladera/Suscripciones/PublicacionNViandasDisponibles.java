package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;

public class PublicacionNViandasDisponibles extends Publicacion {
    private int cantidadDisponible;

    public PublicacionNViandasDisponibles(int cant) {
        this.cantidadDisponible = cant;
        this.tipoPublicacion = TipoPublicacion.N_VIANDAS_DISPONIBLES;
    }

    @Override
    public Boolean verificarCondicion(Publicacion publicacion, Heladera heladera){
        return  publicacion.getTipoPublicacion() == tipoPublicacion  && cantidadDisponible  == heladera.getViandas().size();
    }
}
