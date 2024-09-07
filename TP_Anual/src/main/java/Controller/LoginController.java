package Controller;

import Models.Domain.Personas.Actores.Persona;
import Models.Repository.PseudoBaseDatosUsuario;
import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;

public class LoginController extends Controller {


    public void index(Context context){
        if (context.sessionAttribute("usuarioActual") == null) {
            context.render("sesion/login.hbs");
        } else {
            context.redirect("/");
        }
    }

    public void manejarInicioSesion(Context context) {
        String nombreUsuario = context.formParam("usuario");
        String contrasenia = context.formParam("password");
        context.sessionAttribute("usuarioActual", nombreUsuario);

        Persona usuario = PseudoBaseDatosUsuario.getInstance().searchUser(nombreUsuario);


        if (usuario != null) {
            String idPersona = Integer.toString(usuario.getId());
            context.sessionAttribute("idPersona", idPersona);
            context.redirect("/");
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
