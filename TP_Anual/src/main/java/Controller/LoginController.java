package Controller;

import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;

public class LoginController extends Controller {

    public void create(Context context){
        context.render("/login.hbs");
    }

    public void index(Context context){
        if (context.sessionAttribute("usuarioActual") == null) {
            context.render("/login.hbs");
        } else {
            context.redirect("/index/humano");
        }
    }

    public void show(Context context){
        context.render("main.hbs");
    }

    public void manejarInicioSesion(Context context) {
        String nombreUsuario = context.formParam("user");
        String contrasenia = context.formParam("password");
        context.sessionAttribute("usuarioActual", nombreUsuario);

        // Persona usuario = repositorio.buscarUsuarioPorCredenciales(nombreUsuario,contrasenia);

        if (usuario != null) {
        //      String idPersona = Integer.toString(usuario.getId());
        //    context.sessionAttribute("idPersona", idPersona); // Almacena el ID en la sesión
            context.redirect("/index/humano");
        } else {
            HttpSession httpSession = context.req().getSession();
            httpSession.removeAttribute("usuarioActual");
            context.redirect("/login");
        }

    }


    public void manejarCierreSesion(Context context) {
        HttpSession httpSession = context.req().getSession();
        httpSession.removeAttribute("usuarioActual");
        httpSession.removeAttribute("tipo_rol");
        context.redirect("/login");
    }
}
