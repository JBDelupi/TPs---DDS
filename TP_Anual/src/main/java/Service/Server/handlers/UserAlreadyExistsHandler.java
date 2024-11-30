package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.CapacidadHeladeraException;
import Service.Observabilidad.MetricsRegistry;
import Service.Server.exceptions.UserAlreadyExistsException;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;


public class UserAlreadyExistsHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(UserAlreadyExistsException.class, (e, context) -> {

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.userAlreadyExists").increment();

            context.render("Excepciones/UserAlreadyExists.hbs");
        });
    }
}
