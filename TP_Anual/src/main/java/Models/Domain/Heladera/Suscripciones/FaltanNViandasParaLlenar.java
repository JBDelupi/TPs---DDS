package Models.Domain.Heladera.Suscripciones;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;

public class FaltanNViandasParaLlenar implements ObserverHeladera {
    private Persona colaborador;
    private int n;


    public FaltanNViandasParaLlenar(Persona colaborador, int n) {
        this.colaborador = colaborador;
        this.n = n;

    }

    @Override
    public void update(TipoDePublicacion tipo, Heladera heladera) {
        if (tipo.equals(TipoDePublicacion.FALTAN_N_VIANDAS) && heladera.getCapacidadDeViandas() - heladera.getViandas().size() <= n) {
            MensajeBuilder nuevaPublicacionBuilder = new MensajeBuilder();
            Mensaje unaPublicacion = nuevaPublicacionBuilder
                    .asunto(TipoDePublicacion.FALTAN_N_VIANDAS.toString())
                    .contenido("N viandas faltan")
                    .destinatario(colaborador.getCodigoDeNotificacion() )
                    .construir();

            colaborador.notify(unaPublicacion);
        }
    }
}