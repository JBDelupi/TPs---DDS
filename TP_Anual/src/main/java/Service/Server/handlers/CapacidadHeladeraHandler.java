package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.CapacidadHeladeraException;
import Models.Domain.Excepciones.HeladeraLlenaException;
import io.javalin.Javalin;

public class CapacidadHeladeraHandler extends Controller implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(CapacidadHeladeraException.class, (e, context) -> {
            this.estaLogueado(context);
            context.render("Formas-de-contribucion/contribucionFallida.hbs",this.basicModel(context));
        });
    }
}
