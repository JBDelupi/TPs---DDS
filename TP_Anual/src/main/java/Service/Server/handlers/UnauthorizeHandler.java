package Service.Server.handlers;

import Service.Server.exceptions.UnauthorizedResponseException;
import io.javalin.Javalin;


public class UnauthorizeHandler implements IHandler {

    @Override
    public void setHandle(Javalin app) {
        app.exception(UnauthorizedResponseException.class, (e, context) -> {
            context.render("Excepciones/403.hbs");
        });
    }
}
