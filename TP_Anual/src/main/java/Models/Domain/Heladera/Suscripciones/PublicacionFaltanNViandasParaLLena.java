package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;

public class PublicacionFaltanNViandasParaLLena extends Publicacion{
    private int cantidadFaltante;
    Boolean heladeraLlena;

    public PublicacionFaltanNViandasParaLLena(int cantidadFaltante){
        this.cantidadFaltante = cantidadFaltante;
    }

    public Boolean verificarCondicion(Heladera heladera){
        return tipoPublicacion == TipoPublicacion.FALTAN_N_VIANDAS_PARA_LLENA
                && heladera.getCapacidadDeViandas() == cantidadFaltante
                && heladeraLlena;
    }
}
