package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.CapacidadHeladeraException;
import Service.Observabilidad.MetricsRegistry;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;

public class CapacidadHeladeraHandler extends Controller implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(CapacidadHeladeraException.class, (e, context) -> {
            this.estaLogueado(context);

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.capacidadDeHeladera").increment();

            context.render("Formas-de-contribucion/contribucionFallida.hbs",this.basicModel(context));
        });
    }
}
