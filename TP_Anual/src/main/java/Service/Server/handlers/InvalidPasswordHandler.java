package Service.Server.handlers;

import Service.Server.exceptions.InvalidPasswordException;
import io.javalin.Javalin;

public class InvalidPasswordHandler implements IHandler{

    @Override
    public void setHandle(Javalin app) {
        app.exception(InvalidPasswordException.class, (e, context) -> {
            context.render("errors/invalid_password.hbs");
        });
    }
}
