package Controller.Administrador;

import Controller.Controller;
import Models.Domain.Builder.CredencialDeAccesoBuilder;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Repository.RepoTecnico;
import Service.APIPuntos.AreaCobertura;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.APIPuntos.Punto;
import Service.Validador.CredencialDeAcceso;
import io.javalin.http.Context;

import java.util.Map;
import java.util.random.RandomGenerator;

public class TecnicoController extends Controller {

    private RepoTecnico repo;

    public TecnicoController(RepoTecnico repo){
        this.repo = repo;
    }

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

        repo.agregar(fisico);

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

        String cuil = context.formParam("cuil");
        String latitud = context.formParam("latitud");
        String longitud = context.formParam("longitud");
        String radio = context.formParam("radio");


        Persona persona = (Persona) repo.buscar( Integer.parseInt(idUsuario));

        Tecnico tecnico = new Tecnico(cuil,new AreaCobertura(new Punto(latitud,longitud),radio));

        persona.agregarRol(tecnico);

        repo.modificar(persona);

        context.redirect("/index/administrador");


    }

    public void update(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

        context.render("Tecnico/asignar_rol_tecnico.hbs",model);
    }


}
