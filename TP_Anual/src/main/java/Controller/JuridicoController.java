package Controller;

import Controller.Actores.Usuario;
import Models.Domain.Builder.UsuariosBuilder.JuridicoBuilder;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.Actores.Juridico;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import Models.Repository.PseudoBaseDatosUsuario;
import Models.Repository.RepoColaboradores;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class JuridicoController extends Controller implements ICrudViewsHandler {

    public JuridicoController(Usuario usuario) {
        this.usuario = usuario;
    }

    public JuridicoController() {

    }

    //  @POST
    public void save(Object ... Context) {

        String razonSocial = (String) Context[0];
        TipoJuridico tipoJuridico = (TipoJuridico) Context[1];
        String correo = (String) Context[2];

        JuridicoBuilder juridicoBuilder = new JuridicoBuilder();
        Juridico juridico = juridicoBuilder
                .razonSocial(razonSocial)
                .tipoJuridico(tipoJuridico)
                .correoElectronico(correo)
                .construir();


        RepoColaboradores.getInstance().agregarColaborador(juridico);

    }

    /*************************************** JAVALIN *****************************/

    //@GET

    @Override
    public void index(Context context) {
        context.render("index-inicio/index.hbs");
    }

    //@GET
    @Override
    public void show(Context context) {
        String id = context.sessionAttribute("idPersona");
        Juridico juridico = (Juridico) PseudoBaseDatosUsuario.getInstance().getId(id);

        Map<String, Object> model = new HashMap<>();

        context.render("persona-Juridica/perfilJuridico.hbs", model);
    }

    //@GET
    public void create(Context context){
        context.render("persona-Juridica/registroJuridica.hbs");
    }

    //@POST
    public void save(Context context) {

        String razonSocial =  context.formParam("razon_social") ;
        TipoJuridico tipoJuridico = TipoJuridico.valueOf(context.formParam("tipo_juridico"));
        String correo = context.formParam("correo") ;

        JuridicoBuilder juridicoBuilder = new JuridicoBuilder();
        Juridico juridico = juridicoBuilder
                .razonSocial(razonSocial)
                .tipoJuridico(tipoJuridico)
                .correoElectronico(correo)
                .construir();



        context.redirect("/");
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

}
