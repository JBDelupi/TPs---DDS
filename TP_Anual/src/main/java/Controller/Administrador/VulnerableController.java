package Controller.Administrador;

import Controller.Controller;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import static java.lang.Integer.parseInt;

public class VulnerableController extends Controller implements ICrudViewsHandler {
    @Override
    public void create(Context context) {
        context.render("persona-vulnerable/registroVulnerable.hbs");
    }

    @Override
    public void save(Context context) {
        // TODA LA INFO

       /*
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

        PersonaVulnerable vulnerableNuevo = vulenrableBuilder

                .nombre(nombre)
                .menoresACargo(tieneMenores)
                .construir();

        vulnerableNuevo.setNumeroDocumento(numeroDocumento);

        PseudoBaseDeDatosVulnerables.getInstance().agregar(vulnerableNuevo);

        */
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
