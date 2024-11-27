package Service.Server.handlers;

import Service.Server.exceptions.InvalidPasswordException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class InvalidPasswordHandler implements IHandler{

    @Override
    public void setHandle(Javalin app) {
        app.exception(InvalidPasswordException.class, (e, context) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("contraseniaError", true);
            context.render("sesion/login.hbs",model);
        });
    }
}
