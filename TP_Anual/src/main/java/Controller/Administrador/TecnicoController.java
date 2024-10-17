package Controller.Administrador;

import Controller.Controller;
import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
import Models.Domain.Personas.Actores.Persona;
import Models.Repository.PseudoBaseDatosUsuario;
import Service.APIPuntos.AreaCobertura;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.Map;

public class TecnicoController extends Controller {


    public void save(Context context) {

        String nombre = context.formParam("nombre");
        String apellido = context.formParam("apellido");
      //  TipoDeDocumento tipoDeDocumento = TipoDeDocumento.valueOf(context.formParam("tipoDocumento"));
        String numeroDocumento = context.formParam("documento");
        String cuil = context.formParam("cuil");
        String areaCobertura = context.formParam("areaCobertura");


        TecnicoBuilder builder = new TecnicoBuilder();
//       Tecnico tecnico = TecnicoBuilder
//                .nombre(nombre)
//                .apellido(apellido)
//                .tipoDeDocumento(tipoDeDocumento)
//                .numeroDeDocumento(numeroDocumento)
//                .cuil(cuil)
//                .construir();

        context.redirect("/index/administrador");
    }



    public void index(Context context) {
        this.estaLogueado(context);

        context.render("Tecnico/index_registro_tecnico.hbs");
    }



    public void create(Context context) {
        this.estaLogueado(context);

        context.render("Tecnico/registroTecnico.hbs");
    }



    public void edit(Context context)  {
        String idUsuario = context.formParam("userId");

        Persona persona = PseudoBaseDatosUsuario.getInstance().getId(idUsuario);
        persona.agregarRol(new Tecnico());
        context.redirect("/index/administrador");


    }

    public void update(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

        context.render("Tecnico/asignar_rol_tecnico.hbs",model);
    }


}
