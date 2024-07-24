package Models.Domain.Heladera.Suscripciones;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;

public class NViandasDisponibles implements ObserverHeladera {
    private Colaborador colaborador;
    private int n;

    public NViandasDisponibles(Colaborador colaborador, int n) {
        this.colaborador = colaborador;
        this.n = n;
    }


    public void update(TipoDePublicacion tipo, Heladera heladera) {
        if (tipo.equals(TipoDePublicacion.N_VIANDAS_DISPONIBLES) && heladera.getViandas().size() <= n) {
            MensajeBuilder nuevaPublicacionBuilder = new MensajeBuilder();
            Mensaje unaPublicacion = nuevaPublicacionBuilder
                    .asunto(TipoDePublicacion.N_VIANDAS_DISPONIBLES.toString())
                    .contenido("Hay de viandas: " + heladera.getViandas().size())
                    .destinatario(colaborador.getCorreoElectronico())
                    .construir();

            colaborador.notify(unaPublicacion);
        }
    }
}
