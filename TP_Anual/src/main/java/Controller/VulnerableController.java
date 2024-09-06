package Controller;

import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Builder.UsuariosBuilder.VulnerableBuilder;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.PersonaVulnerable;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosProducto;
import Models.Repository.PseudoBaseDeDatosVulnerables;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class VulnerableController extends Controller implements ICrudViewsHandler {
    @Override
    public void create(Context context) {
        context.render("persona-vulnerable/registroVulnerable.hbs");
    }

    @Override
    public void save(Context context) {
        // TODA LA INFO
            String direccion = context.formParam("direccion");
            String nombre = context.formParam("nombre");
            String apellido = context.formParam("apellido");
            String fechaNacimiento = context.formParam("fechaNacimiento");
            String tipoDocumento = context.formParam("tipoDocumento");
            String numeroDocumento = context.formParam("documento");
            int tieneMenores = parseInt(context.formParam("menoresACargo"));
            String cantidadMenores = context.formParam("cantidadMenores");
            String estaEnLaCalle = context.formParam("situacionCalle");
            String domicilioAct = context.formParam("domicilioActual");

        VulnerableBuilder vulenrableBuilder = new VulnerableBuilder();

        PersonaVulnerable vulenrableNuevo = vulenrableBuilder

                .nombre(nombre)
                .menoresACargo(tieneMenores)
                .construir();

        vulenrableNuevo.setNumeroDocumento(numeroDocumento);

        PseudoBaseDeDatosVulnerables.getInstance().agregar(vulenrableNuevo);


        context.redirect("/");
    }

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }
}
