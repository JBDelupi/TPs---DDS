package Controller;

import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Repository.RepoPersona;
import Service.SSO.AdapterGoogleSSO;
import Service.SSO.GoogleAdaptado;
import Service.SSO.Sso;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Validador.CredencialDeAcceso;
import com.fasterxml.jackson.databind.JsonNode;
import io.javalin.http.Context;

public class LoginSSOController extends Controller {
    private final RepoPersona repo;
    public LoginSSOController(RepoPersona repoColaborador) {
        this.repo = repoColaborador;
    }

    public void redirectToSSO(Context context) {
        String authUrl = ssoService.verAutorizacion();
        context.redirect(authUrl);
    }

    public void handleCallback(Context context) {
        String code = context.queryParam("code");
        String accessToken = ssoService.generarToken(code);

        if (accessToken != null) {
            JsonNode userInfo = ssoService.infoUsuario(accessToken);

            Persona persona = repo.existeUsuarioSso( userInfo.get("email").asText());
            if( persona == null) {
                 persona = crearPersonaDesdeSSO(userInfo);
            }

            context.sessionAttribute("usuario", persona);
            context.sessionAttribute("idPersona", Integer.toString(persona.getId()));
            context.sessionAttribute("rolTipo", persona.getTipoUsuario().toString());

            context.redirect("/index/" + persona.getTipoUsuario().toString().toLowerCase());
        } else {
            context.status(500).result("Error al obtener el token.");
        }
    }
    private Persona crearPersonaDesdeSSO(JsonNode userInfo) {

        String nombre = userInfo.get("given_name").asText();
        String correo = userInfo.get("email").asText();
        String apellido = userInfo.get("family_name").asText();

        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso(correo, "1");

        FisicoBuilder builder = new FisicoBuilder();
        Persona persona = builder
                .nombre(nombre)
                .apellido(apellido)
                .correoElectronico(correo)
                .credencialDeAcceso(credencialDeAcceso)
                .construir();

        Colaborador colaborador = new Colaborador(0.0);
        persona.agregarRol(colaborador);

        repo.agregar(persona);

        return persona;
    }

}
