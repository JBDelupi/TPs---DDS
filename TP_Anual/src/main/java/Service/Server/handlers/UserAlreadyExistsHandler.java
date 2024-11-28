package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.CapacidadHeladeraException;
import Service.Server.exceptions.UserAlreadyExistsException;
import io.javalin.Javalin;


public class UserAlreadyExistsHandler implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(UserAlreadyExistsException.class, (e, context) -> {
            context.render("Excepciones/UserAlreadyExists.hbs");
        });
    }
}
