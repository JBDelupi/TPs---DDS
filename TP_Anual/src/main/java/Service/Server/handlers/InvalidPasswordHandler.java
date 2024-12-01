package Service.Server.handlers;

import Service.Observabilidad.MetricsRegistry;
import Service.Server.exceptions.InvalidPasswordException;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.HashMap;
import java.util.Map;

public class InvalidPasswordHandler implements IHandler{

    @Override
    public void setHandle(Javalin app) {
        app.exception(InvalidPasswordException.class, (e, context) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("contraseniaError", true);

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.invalidPassword").increment();

            context.render("Sesion/login.hbs",model);
        });
    }
}
