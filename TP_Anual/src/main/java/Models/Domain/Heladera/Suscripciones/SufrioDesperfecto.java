package Models.Domain.Heladera.Suscripciones;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;


public class SufrioDesperfecto implements ObserverHeladera {
    private Colaborador colaborador;

    public SufrioDesperfecto(Colaborador colaborador){
        this.colaborador = colaborador;
    }


    @Override
    public void update(TipoDePublicacion publicacion, Heladera heladera) {
        if( publicacion.equals(TipoDePublicacion.SUFRIO_DESPERFECTO) ){
            MensajeBuilder nuevaPublicacionBuilder = new MensajeBuilder();
            Mensaje unaPublicacion = nuevaPublicacionBuilder
                    .asunto(TipoDePublicacion.SUFRIO_DESPERFECTO.toString())
                    .contenido("Se rompio")
                    .destinatario(colaborador.getCorreoElectronico())
                    .construir();

            colaborador.notify(unaPublicacion);
        }
    }
    public String armarDescripcion(Heladera heladera){

        return null;
    }


}




