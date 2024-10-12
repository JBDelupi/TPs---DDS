package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.Builder.CredencialDeAccesoBuilder;
import Models.Domain.Builder.UsuariosBuilder.ColaboradorBuilder;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Builder.UsuariosBuilder.JuridicoBuilder;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Personas.Actores.Juridico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Personas.Utilidades.TipoJuridico;
import Models.Repository.PseudoBaseDatosUsuario;
import Models.Repository.RepoColaboradores;
import Service.Server.ICrudViewsHandler;
import Service.Validador.CredencialDeAcceso;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class JuridicoController extends Controller implements ICrudViewsHandler {

    public JuridicoController(Persona usuario) {
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


       // RepoColaboradores.getInstance().agregarColaborador(juridico);

    }

    /*************************************** JAVALIN *****************************/

    //@GET

    @Override
    public void index(Context context) {
        this.estaLogueado(context);

        context.render("index-inicio/index_Juridica.hbs", basicModel(context));
    }

    //@GET
    @Override
    public void show(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

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
        String correo = context.formParam("correo");

        CredencialDeAccesoBuilder credencialDeAccesoBuilder = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso = credencialDeAccesoBuilder
                .contrasenia(context.formParam("contrasenia"))
                .nombreUsuario(context.formParam("nombre_usuario"))
                .construir();

        JuridicoBuilder juridicoBuilder = new JuridicoBuilder();
        Juridico juridico = juridicoBuilder
                .razonSocial(razonSocial)
                .tipoJuridico(tipoJuridico)
                .correoElectronico(correo)
                .credencialDeAcceso(credencialDeAcceso)
                .construir();

        juridico.setId(RandomGenerator.getDefault().nextInt(0,100));

        ColaboradorBuilder colaboradorBuilder = new ColaboradorBuilder();
        Colaborador colaborador = colaboradorBuilder.construir(juridico);

        juridico.agregarRol(colaborador);

        PseudoBaseDatosUsuario.getInstance().agregar(juridico);

        context.redirect("/login");
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    public void consultarContribuciones(Context context){
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        List<Contribucion> contribuciones = ((Colaborador)usuario.getRol(TipoRol.COLABORADOR)).getContribuciones();

        model.put("contribuciones",contribuciones);

        context.render("FormasDeContribucion/misContribuciones.hbs", model);
    }


}
