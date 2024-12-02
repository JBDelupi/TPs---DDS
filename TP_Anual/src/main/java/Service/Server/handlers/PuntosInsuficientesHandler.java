package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.NoTienePuntosCanjeException;
import Service.Observabilidad.MetricsRegistry;
import io.javalin.Javalin;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.HashMap;
import java.util.Map;

public class PuntosInsuficientesHandler extends Controller implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(NoTienePuntosCanjeException.class, (e, context) -> {
            this.estaLogueado(context);

            //Incremento la metrica
            MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
            registry.counter("dds.exception.puntosInsuficientes").increment();

            context.render("Producto/sinPuntos.hbs", this.basicModel(context));
        });
    }
}
