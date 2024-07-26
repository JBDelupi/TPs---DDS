package Models.Domain.Heladera.Suscripciones;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.Sugerencia.SistemaDeRedistribucion;
import Models.Domain.Heladera.Suscripciones.Sugerencia.Sugerencia;
import Models.Domain.Heladera.Suscripciones.Sugerencia.SugerenciaCompuesta;
import Models.Domain.Personas.Actores.Colaborador;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;
import Service.SistemaDeGeolocalizacion.PseudoBaseDatosHeladera;


public class SufrioDesperfecto implements ObserverHeladera {
    private Colaborador colaborador;

    public SufrioDesperfecto(Colaborador colaborador){
        this.colaborador = colaborador;
    }


    @Override
    public void update(TipoDePublicacion publicacion, Heladera heladera) {
        if( publicacion.equals(TipoDePublicacion.SUFRIO_DESPERFECTO) ){
            MensajeBuilder nuevaPublicacionBuilder = new MensajeBuilder();

            PseudoBaseDatosHeladera base = new PseudoBaseDatosHeladera();
            SistemaDeRedistribucion sistema = new SistemaDeRedistribucion(base.baseHeladeras);
            Sugerencia sugerencia = sistema.generarSugerencia(heladera.getViandas().size());

            Mensaje unaPublicacion = nuevaPublicacionBuilder
                    .asunto(TipoDePublicacion.SUFRIO_DESPERFECTO.toString())
                    .contenido(sugerencia.mostrar())
                    .destinatario(colaborador.getCorreoElectronico())
                    .construir();

            colaborador.notify(unaPublicacion);
        }
    }
    public String armarDescripcion(Heladera heladera){

        return null;
    }


}




