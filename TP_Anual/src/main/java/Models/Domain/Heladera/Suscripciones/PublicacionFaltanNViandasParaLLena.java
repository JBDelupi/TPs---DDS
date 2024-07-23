package Models.Domain.Heladera.Suscripciones;

import Models.Domain.Heladera.Heladera;

public class PublicacionFaltanNViandasParaLLena extends Publicacion{
    private int cantidadFaltante;


    public PublicacionFaltanNViandasParaLLena(int cantidadFaltante){
        this.cantidadFaltante = cantidadFaltante;
        tipoPublicacion = TipoPublicacion.FALTAN_N_VIANDAS_PARA_LLENA;

    }

    @Override
    public Boolean verificarCondicion(Publicacion publicacion,Heladera heladera){
        this.descripcion = "Cantidad " + String.valueOf(heladera.getViandas().size() );
        return tipoPublicacion == publicacion.getTipoPublicacion() &&
                (heladera.getCapacidadDeViandas() - heladera.getViandas().size()  == cantidadFaltante || heladera.getEstaLlena());

    }
}
