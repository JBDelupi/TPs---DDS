package Models.Domain.Heladera.Suscripciones;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Mensaje.MensajeBienvenida;
import Service.Notificacion.Mensaje.MensajeSuscripcion;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.random.RandomGenerator;

@Getter


public class NViandasDisponibles implements ObserverHeladera {

    int id;

    @Transient
    private Persona colaborador;

    @Column(name = "cantidad_de_viandas")
    private int n;

    @Column(name = "nombre")
    private String nombre;

    public NViandasDisponibles(Persona colaborador, int n) {
        this.id = RandomGenerator.getDefault().nextInt(0,1000);
        this.colaborador = colaborador;
        this.n = n;
        nombre = "Quedan N viandas disponibles";
    }


    public void update(TipoDePublicacion tipo, Heladera heladera) {
        if (tipo.equals(TipoDePublicacion.N_VIANDAS_DISPONIBLES) && heladera.getViandas().size() <= n) {
            Mensaje unaPublicacion = new MensajeSuscripcion(colaborador.getCodigoDeNotificacion(), "Quedan " + heladera.getViandas().size() + " Viandas disponibles");
            colaborador.notify(unaPublicacion);
        }
    }


}
