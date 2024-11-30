package Service.Server.handlers;

import Service.Observabilidad.MetricsRegistry;
import Service.Server.exceptions.AccessDeniedException;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;

public class AccessDeniedHandler implements IHandler{

    @Override
    public void setHandle(Javalin app) {
        app.exception(AccessDeniedException.class, (e, context) -> {

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.accessDenied").increment();

            context.render("Excepciones/401.hbs");
        });
    }
}
