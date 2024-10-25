package Models.Domain.Heladera.Suscripciones;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.Sugerencia.SistemaDeRedistribucion;
import Models.Domain.Heladera.Suscripciones.Sugerencia.Sugerencia;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Mensaje.MensajeSuscripcion;
import lombok.Getter;

import java.util.random.RandomGenerator;
@Getter
public class SufrioDesperfecto implements ObserverHeladera {
    int id;
    private Persona colaborador;
    private String nombre;

    public SufrioDesperfecto(Persona colaborador){
        this.id = RandomGenerator.getDefault().nextInt(0,1000);
        this.colaborador = colaborador;
        this.nombre = "Sufrio un desperfecto";
    }


    @Override
    public void update(TipoDePublicacion publicacion, Heladera heladera) {
        if( publicacion.equals(TipoDePublicacion.SUFRIO_DESPERFECTO) ){

            Sugerencia sugerencia = SistemaDeRedistribucion.generarSugerencia(heladera.getViandas().size());

            Mensaje unaPublicacion = new MensajeSuscripcion(colaborador.getCodigoDeNotificacion(), "La heladera ID:"+ heladera.getId() + " Sufrio un desperfecto - Heladeras donde se recomienda distribuir: " + sugerencia.mostrar());

            colaborador.notify(unaPublicacion);
        }
    }

}




