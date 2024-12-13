package Service.Server.handlers;

import Service.Server.exceptions.UserNoLongerExistsException;
import io.javalin.Javalin;

public class UserNoLongerExistsHandler implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(UserNoLongerExistsException.class, (e, context) -> {

            context.render("Excepciones/UserNoLongerExists.hbs");
        });
    }
}
