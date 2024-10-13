package Models.Domain.Heladera.Suscripciones;


import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Notificacion.Mensaje;
import Service.Notificacion.MensajeBuilder;

import java.util.random.RandomGenerator;

public class FaltanNViandasParaLlenar implements ObserverHeladera {
    int id;
    private Persona colaborador;
    private int n;
    private String nombre;


    public FaltanNViandasParaLlenar(Persona colaborador, int n) {
        this.id = RandomGenerator.getDefault().nextInt(0,1000);
        this.colaborador = colaborador;
        this.n = n;
        this.nombre = "Faltan N viandas para llenar";
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

    @Override
    public Persona getColaborador(){
        return colaborador;
    }

    @Override
    public String getNombre() {
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