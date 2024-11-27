package Models.Domain.Heladera.Suscripciones;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.Sugerencia.SistemaDeRedistribucion;
import Models.Domain.Heladera.Suscripciones.Sugerencia.Sugerencia;
import Models.Domain.Heladera.Suscripciones.Utilidades.TipoDePublicacion;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Mensaje.MensajeSuscripcion;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter

@Entity
@DiscriminatorValue("SufrioDesperfecto")
@NoArgsConstructor

public class SufrioDesperfecto extends ObserverHeladera {

   @ManyToOne
    private Persona colaborador;

    @Column(name = "nombre")

    private String nombre;

    public SufrioDesperfecto(Persona colaborador){
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




