package Controller.Administrador;

import Controller.Controller;
import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
import Service.APIPuntos.AreaCobertura;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

public class TecnicoController extends Controller implements ICrudViewsHandler {

    @Override
    public void save(Context context) {

        String nombre = context.formParam("nombre");
        String apellido = context.formParam("apellido");
      //  TipoDeDocumento tipoDeDocumento = TipoDeDocumento.valueOf(context.formParam("tipoDocumento"));
        String numeroDocumento = context.formParam("documento");
        String cuil = context.formParam("cuil");
        String areaCobertura = context.formParam("areaCobertura");

        TecnicoBuilder builder = new TecnicoBuilder();
//        Tecnico tecnico = TecnicoBuilder
//                .nombre(nombre)
//                .apellido(apellido)
//                .tipoDeDocumento(tipoDeDocumento)
//                .numeroDeDocumento(numeroDocumento)
//                .cuil(cuil)
//                .construir();

        context.redirect("/index/administrador");
    }



    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        context.render("Tecnico/registroTecnico.hbs");
    }

    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }
}
