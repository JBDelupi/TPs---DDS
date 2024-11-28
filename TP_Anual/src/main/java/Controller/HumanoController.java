package Controller;

import Models.Domain.Builder.CredencialDeAccesoBuilder;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Domain.Tarjetas.TarjetaAccesos;
import Models.Repository.RepoPersona;

import Service.Validador.Encriptador;
import Service.Notificacion.Notificacion;
import Service.Notificacion.StrategyMedioDeNotificacion;
import Service.Validador.CredencialDeAcceso;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.Map;

public class HumanoController extends Controller  {

    private final RepoPersona repo;

    public HumanoController(RepoPersona repo) {
        this.repo = repo;
    }



    public void create(Context context){

        context.render("Persona-humana/registroHumana.hbs");

    }

    public void save(Context context){

        String nombre = context.formParam("nombre") ;
        String apellido = context.formParam("apellido") ;
        LocalDate fechaNacimiento = LocalDate.parse(context.formParam("fecha_nacimiento"));
        String correo = context.formParam("correo");
        String nroDocumento = context.formParam("documento");
        String calle = context.formParam("calle");
        String numero = context.formParam("numero");
        String localidad = context.formParam("localidad");
        TipoDeDocumento tipoDeDocumento = TipoDeDocumento.valueOf(context.formParam("tipo_documento"));
        String contrasenia = context.formParam("contrasenia");
        Encriptador encriptador = new Encriptador();

        // PARA ENCRIPTAR LA CONTRASEÑA
        //String contraseniaEnciptada = encriptador.encriptarMD5(contraseniaSinEncriptar);

        repo.existeUsuario(context.formParam("nombre_usuario"));

        CredencialDeAccesoBuilder credencialDeAccesoBuilder = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso = credencialDeAccesoBuilder
                .contrasenia(contrasenia)
                .nombreUsuario(context.formParam("nombre_usuario"))
                .construir();

        Direccion direccion = new Direccion();
        direccion.setCalle(calle);
        direccion.setNumero(numero);
        direccion.setLocalidad(localidad);

        FisicoBuilder fisicoBuilder = new FisicoBuilder();
        Fisico fisico = fisicoBuilder
                .nombre(nombre)
                .apellido(apellido)
                .fechaNacimiento(fechaNacimiento)
                .correoElectronico(correo)
                .numeroDocumento(nroDocumento)
                .credencialDeAcceso(credencialDeAcceso)
                .tipoDocumento(tipoDeDocumento)
                .direccion(direccion)
                .construir();

        Colaborador colaborador = new Colaborador(0.0);
        TarjetaAccesos tarjetaAccesos = new TarjetaAccesos(fisico);
        colaborador.setTarjeta(tarjetaAccesos);
        fisico.agregarRol(colaborador);
        //  new MensajeBienvenida(persona.getCorreElectronico(), tarjetaAccesos.getCodigo());

        repo.agregar(fisico);

        context.redirect("/login");
    }




    public void index(Context context){
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

        model.put("colaborador", usuario.checkRol(TipoRol.COLABORADOR));
        model.put("tecnico", usuario.checkRol(TipoRol.TECNICO));

        context.render("Index-inicio/index_Humana.hbs", model);

    }


    public void show(Context context){
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);
        model.put("humano", model);

        context.render("Persona-humana/perfilHumana.hbs", model);
    }


    public void update(Context context){
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);
        this.asignarParametros(context);
        repo.agregar(this.usuario);

        context.redirect("/persona/fisico/" + usuario.getId());
    }

    public void asignarParametros(Context context){
         if ( context.formParam("medioNotificacion") != null ) {
             Notificacion medioDeNotificacion = StrategyMedioDeNotificacion.strategy( context.formParam("medioNotificacion") );
             getUsuario().setMedioDeNotificacion(medioDeNotificacion);
             getUsuario().setCodigoDeNotificacion(context.formParam("codigo"));
         }
    }

}
