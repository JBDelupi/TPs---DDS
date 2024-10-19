package Controller.Administrador;

import Controller.Controller;
import Models.Domain.Builder.CredencialDeAccesoBuilder;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.Builder.UsuariosBuilder.TecnicoBuilder;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Repository.PseudoBaseDatosUsuario;
import Service.APIPuntos.AreaCobertura;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.APIPuntos.Punto;
import Service.Server.ICrudViewsHandler;
import Service.Validador.CredencialDeAcceso;
import io.javalin.http.Context;

import java.util.Map;
import java.util.random.RandomGenerator;

public class TecnicoController extends Controller {


    public void save(Context context) {

        String nombre = context.formParam("nombre");
        String apellido = context.formParam("apellido");
        TipoDeDocumento tipoDeDocumento = TipoDeDocumento.valueOf(context.formParam("tipo_documento"));
        String numeroDocumento = context.formParam("documento");
        String cuil = context.formParam("cuil");
        String latitud = context.formParam("latitud");
        String longitud = context.formParam("longitud");
        String radio = context.formParam("radio");
        String correo = context.formParam("correo");

        CredencialDeAccesoBuilder credencialDeAccesoBuilder = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso = credencialDeAccesoBuilder
                .contrasenia(context.formParam("contrasenia"))
                .nombreUsuario(context.formParam("nombre_usuario"))
                .construir();

        Tecnico tecnico = new Tecnico(cuil, new AreaCobertura(new Punto(latitud,longitud),radio) );

        FisicoBuilder fisicoBuilder = new FisicoBuilder();
        Fisico fisico = fisicoBuilder
                .nombre(nombre)
                .apellido(apellido)
                .correoElectronico(correo)
                .tipoDocumento(tipoDeDocumento)
                .numeroDocumento(numeroDocumento)
                .credencialDeAcceso(credencialDeAcceso)
                .rol(tecnico)
                .construir();

        fisico.setId(RandomGenerator.getDefault().nextInt(0,100));

        PseudoBaseDatosUsuario.getInstance().agregar(fisico);

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
