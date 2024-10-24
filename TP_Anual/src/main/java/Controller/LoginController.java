package Controller;

import Models.Domain.Personas.Actores.Persona;
import Models.Repository.PseudoBaseDatosUsuario;
import Models.Repository.Repositorio;
import Service.Validador.CredencialDeAcceso;
import io.javalin.http.Context;
import jakarta.servlet.http.HttpSession;

public class LoginController extends Controller {

    private static final String USER_SESSION_KEY = "usuario";
    private static final String USERNAME_PARAM = "usuario";
    private static final String PASSWORD_PARAM = "password";

    public void index(Context context) {
        Persona usuario = context.sessionAttribute(USER_SESSION_KEY);

        if (usuario == null) {
            context.render("sesion/login.hbs");
        } else {
            String rolTipo = usuario.getTipoUsuario().toString().toLowerCase();
            context.redirect("/index/" + rolTipo);
        }
    }

    public void manejarInicioSesion(Context context) {
        String nombreUsuario = context.formParam(USERNAME_PARAM);
        String contrasenia = context.formParam(PASSWORD_PARAM);

        Repositorio repo = new Repositorio(Persona.class);

        Persona usuario = (Persona) repo.credenciales(new CredencialDeAcceso(nombreUsuario,contrasenia));

        //if (usuario != null && usuario.getCredencialDeAcceso().getContrasenia().equals(contrasenia)) {
        if (usuario != null) {
            context.sessionAttribute(USER_SESSION_KEY, usuario);
            String idPersona = Integer.toString(usuario.getId());
            context.sessionAttribute("idPersona", idPersona);
            String rolTipo = usuario.getTipoUsuario().toString().toLowerCase();
            context.sessionAttribute("rolTipo", usuario.getTipoUsuario().toString());
            context.redirect("/index/" + rolTipo);
        } else {
            context.sessionAttribute(USER_SESSION_KEY, null);
            context.redirect("/login");
        }
    }

    public void manejarCierreSesion(Context context) {
        HttpSession httpSession = context.req().getSession();
        httpSession.removeAttribute(USER_SESSION_KEY);
        context.redirect("/login");
    }
}
