package Service.Server.handlers;

import Service.Observabilidad.MetricsRegistry;
import Service.Server.exceptions.UnauthorizedResponseException;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;


public class UnauthorizeHandler implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.exception(UnauthorizedResponseException.class, (e, context) -> {

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.unauthorized").increment();

            context.render("Excepciones/403.hbs");
        });
    }
}
