package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.HeladeraLlenaException;
import Service.Observabilidad.MetricsRegistry;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Map;

public class HeladeraLlenaHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(HeladeraLlenaException.class, (e, context) -> {

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.heladeraLlena").increment();

            context.render("heladera/heladeraLlena.hbs");
        });
    }
}
