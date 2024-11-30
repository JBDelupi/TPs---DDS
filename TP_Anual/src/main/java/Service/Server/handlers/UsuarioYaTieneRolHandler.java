package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.UsuarioYaTieneRolException;
import Service.Observabilidad.MetricsRegistry;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.HashMap;
import java.util.Map;

public class UsuarioYaTieneRolHandler extends Controller implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(UsuarioYaTieneRolException.class, (e, context) -> {
            this.estaLogueado(context);

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.usuarioYaTieneRol").increment();

            Map<String, Object> model = this.basicModel(context);
            model.put("yaTieneRol", true);
            context.render("Tecnico/asignar_rol_tecnico.hbs",model);
        });
    }
}
