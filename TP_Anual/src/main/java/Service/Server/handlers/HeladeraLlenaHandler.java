package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.HeladeraLlenaException;
import io.javalin.Javalin;

import java.util.Map;

public class HeladeraLlenaHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(HeladeraLlenaException.class, (e, context) -> {
            context.render("heladera/heladeraLlena.hbs");
        });
    }
}
