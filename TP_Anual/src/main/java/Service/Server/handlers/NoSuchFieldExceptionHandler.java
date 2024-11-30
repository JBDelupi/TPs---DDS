package Service.Server.handlers;

import Controller.Controller;
import Service.Observabilidad.MetricsRegistry;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Map;
import java.util.NoSuchElementException;

public class NoSuchFieldExceptionHandler extends Controller implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(NoSuchElementException.class, (e, context) -> {
            Map<String, Object> model = this.basicModel(context);
            model.put("Error", true);

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.NoSuchField").increment();

            context.render("/Tecnico/asignar_rol_tecnico.hbs",model);
        });
    }
}
