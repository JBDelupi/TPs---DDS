package Service.Server.handlers;

import Controller.Controller;
import io.javalin.Javalin;

import java.util.Map;
import java.util.NoSuchElementException;

public class NoSuchFieldExceptionHandler extends Controller implements IHandler {
    @Override
    public void setHandle(Javalin app) {
        app.exception(NoSuchElementException.class, (e, context) -> {
            Map<String, Object> model = this.basicModel(context);
            model.put("Error", true);
            context.render("/Tecnico/asignar_rol_tecnico.hbs",model);
        });
    }
}
