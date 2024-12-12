package Controller;

import Models.Domain.Builder.CredencialDeAccesoBuilder;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.EntityManager.EntityManagerHelper;
import Models.Repository.RepoPersona;
import Service.APIPuntos.AreaCobertura;
import Models.Domain.Personas.Actores.Tecnico;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Service.APIPuntos.Punto;
import Service.Observabilidad.MetricsRegistry;
import Service.Validador.CredencialDeAcceso;
import Service.Validador.Encriptador;
import io.javalin.http.Context;
import io.micrometer.core.instrument.MeterRegistry;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TecnicoController extends Controller {

    private final RepoPersona repo;

    public TecnicoController(RepoPersona repo){
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
        String contrasenia = context.formParam("contrasenia");
        String usuario = context.formParam("nombre_usuario");

        repo.existeUsuario(usuario);

        // PARA ENCRIPTAR
        Encriptador encriptador = Encriptador.getInstancia();
        String contraseniaEncriptada = encriptador.encriptarMD5(contrasenia);

        CredencialDeAccesoBuilder credencialDeAccesoBuilder = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso = credencialDeAccesoBuilder
                .contrasenia(contraseniaEncriptada)
                .nombreUsuario(usuario)
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

        //Incremento la metrica
        MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
        registry.counter("dds.tecnicosCreados").increment();

        Map<String,Object> model = new HashMap<>();
        model.put("persona",fisico);
        model.put("fecha_actual", LocalDate.now());
        model = this.basicModel(context);
        context.render("Tecnico/registro_exitoso.hbs",model);
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

        Persona persona =  repo.buscarChequeandoRol(TipoRol.TECNICO,Integer.parseInt(idUsuario));

        Tecnico tecnico = new Tecnico(cuil,new AreaCobertura(new Punto(latitud,longitud),radio));

        persona.agregarRol(tecnico);

        repo.modificar(persona);

        //Incremento la metrica
        MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
        registry.counter("dds.rolTecnicoAgregado").increment();

        Map<String,Object> model = this.basicModel(context);
        model.put("persona",persona);
        model.put("fecha_actual", LocalDate.now());

        context.render("Tecnico/rol_exito_asignado.hbs",model);


    }

    public void update(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        context.render("Tecnico/asignar_rol_tecnico.hbs",model);
    }


}
