package Models.Domain.Heladera.Suscripciones;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;

public class NViandasDisponibles implements ObserverHeladera {
    private Persona colaborador;
    private int n;

    public NViandasDisponibles(Persona colaborador, int n) {
        this.colaborador = colaborador;
        this.n = n;
    }


    public void update(TipoDePublicacion tipo, Heladera heladera) {
        if (tipo.equals(TipoDePublicacion.N_VIANDAS_DISPONIBLES) && heladera.getViandas().size() <= n) {
            MensajeBuilder nuevaPublicacionBuilder = new MensajeBuilder();
            Mensaje unaPublicacion = nuevaPublicacionBuilder
                    .asunto(TipoDePublicacion.N_VIANDAS_DISPONIBLES.toString())
                    .contenido("Hay " + heladera.getViandas().size() + "viandas en la heladera: " + heladera.getDireccion())
                    .destinatario(colaborador.getCodigoDeNotificacion())
                    .construir();

            colaborador.notify(unaPublicacion);
        }
    }

    @Override
    public Persona getColaborador(){
        return colaborador;
    }
}
