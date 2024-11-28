package Service.Server.handlers;

import Controller.Controller;
import Models.Domain.Excepciones.UsuarioYaTieneRolException;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class UsuarioYaTieneRolHandler extends Controller implements IHandler{
    @Override
    public void setHandle(Javalin app) {
        app.exception(UsuarioYaTieneRolException.class, (e, context) -> {
            this.estaLogueado(context);
            Map<String, Object> model = this.basicModel(context);
            model.put("yaTieneRol", true);
            context.render("Tecnico/asignar_rol_tecnico.hbs",model);
        });
    }
}
