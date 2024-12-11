package Service.Server.handlers;

import Controller.Controller;
import Service.Observabilidad.MetricsRegistry;
import Service.Server.exceptions.AccessDeniedException;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.HashMap;
import java.util.Map;


public class AccessDeniedHandler extends Controller implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.exception(AccessDeniedException.class, (e, context) -> {
            Map<String, Object> model = new HashMap<>();

            if(context.sessionAttribute("usuario") != null){
                this.estaLogueado(context);
                model = this.basicModel(context);
                model.put("estaSesion",true);
            }

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.accessDenied").increment();

            context.render("Excepciones/401.hbs",model);
        });
    }
}
