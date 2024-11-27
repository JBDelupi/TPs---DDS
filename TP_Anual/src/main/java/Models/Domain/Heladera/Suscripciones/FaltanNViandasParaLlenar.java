package Models.Domain.Heladera.Suscripciones;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.Utilidades.TipoDePublicacion;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje.Mensaje;
import Service.Notificacion.Mensaje.MensajeSuscripcion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter


@Entity
@DiscriminatorValue("FaltanNViandasParaLlenar")
@NoArgsConstructor
public class FaltanNViandasParaLlenar extends ObserverHeladera {

    @ManyToOne()
    private Persona colaborador;

    @Column(name = "cantidad_de_viandas")
    private int n;

    @Column(name = "nombre")
    private String nombre;


    public FaltanNViandasParaLlenar(Persona colaborador, int n) {
        this.colaborador = colaborador;
        this.n = n;
        this.nombre = "Faltan N viandas para llenar";
    }

    @Override
    public void update(TipoDePublicacion tipo, Heladera heladera) {
        if (tipo.equals(TipoDePublicacion.FALTAN_N_VIANDAS) && heladera.getCapacidadDeViandas() - heladera.getViandas().size() <= n) {
            Mensaje unaPublicacion = new MensajeSuscripcion(colaborador.getCodigoDeNotificacion(), "Faltan" + (heladera.getCapacidadDeViandas() - heladera.getViandas().size()) + "Viandas para que se llenar");
            colaborador.notify(unaPublicacion);
        }
    }


}