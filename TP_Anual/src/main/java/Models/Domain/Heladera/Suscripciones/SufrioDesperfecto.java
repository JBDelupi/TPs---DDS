package Models.Domain.Heladera.Suscripciones;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.Sugerencia.SistemaDeRedistribucion;
import Models.Domain.Heladera.Suscripciones.Sugerencia.Sugerencia;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;
import Models.Repository.PseudoBaseDatosHeladera;
import lombok.Getter;
import retrofit2.http.GET;

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
            MensajeBuilder nuevaPublicacionBuilder = new MensajeBuilder();

            PseudoBaseDatosHeladera base = new PseudoBaseDatosHeladera();
            SistemaDeRedistribucion sistema = new SistemaDeRedistribucion(base.baseHeladeras);
            Sugerencia sugerencia = sistema.generarSugerencia(heladera.getViandas().size());

            Mensaje unaPublicacion = nuevaPublicacionBuilder
                    .asunto(TipoDePublicacion.SUFRIO_DESPERFECTO.toString())
                    .contenido(sugerencia.mostrar())
                    .destinatario(colaborador.getCodigoDeNotificacion())
                    .construir();

            colaborador.notify(unaPublicacion);
        }
    }
    public String armarDescripcion(Heladera heladera){

        return null;
    }

    @Override
    public Persona getColaborador(){
        return colaborador;
    }

    @Override
    public String getNombre(){
        return nombre;
    }

}




