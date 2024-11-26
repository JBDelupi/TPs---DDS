package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.NoTienePuntosCanjeException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class PuntosInsuficientesHandler extends Controller implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(NoTienePuntosCanjeException.class, (e, context) -> {
            this.estaLogueado(context);
            context.render("producto/sinPuntos.hbs", this.basicModel(context));
        });
    }
}
