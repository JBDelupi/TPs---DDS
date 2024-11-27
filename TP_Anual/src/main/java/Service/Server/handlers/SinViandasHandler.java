package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.SinViandasException;
import io.javalin.Javalin;

import java.util.Map;

public class SinViandasHandler extends Controller implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(SinViandasException.class, (e, context) -> {
            this.estaLogueado(context);
            context.render("Formas-de-contribucion/contribucionFallida.hbs",this.basicModel(context));
        });

    }
}
