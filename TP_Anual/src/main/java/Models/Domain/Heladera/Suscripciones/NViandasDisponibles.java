package Models.Domain.Heladera.Suscripciones;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;

import java.util.random.RandomGenerator;

public class NViandasDisponibles implements ObserverHeladera {
    int id;
    private Persona colaborador;
    private int n;
    private String nombre;

    public NViandasDisponibles(Persona colaborador, int n) {
        this.id = RandomGenerator.getDefault().nextInt(0,1000);
        this.colaborador = colaborador;
        this.n = n;
        nombre = "Quedan N viandas disponibles";
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

    @Override
    public String getNombre(){
        return nombre;
    }

    public int getN() {
        return n;
    }

    @Override
    public int getId() {
        return id;
    }
}
