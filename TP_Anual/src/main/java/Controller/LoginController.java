package Controller;

import Models.Domain.DatosPersonales.TipoDeDocumento;
import Models.Domain.Personas.Actores.Humano;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LoginController extends Controller {


    public void create(Context context){
        context.render("/login.hbs");
    }

    public void index(Context context){
        if (context.sessionAttribute("usuarioActual") == null) {
            context.render("/login.hbs");
        } else {
            context.redirect("/index");
        }
    }

    public void show(Context context){
        context.render("main.hbs");
    }


}
