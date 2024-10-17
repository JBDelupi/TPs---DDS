package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.Builder.CredencialDeAccesoBuilder;
import Models.Domain.Builder.UsuariosBuilder.ColaboradorBuilder;
import Models.Domain.Builder.UsuariosBuilder.FisicoBuilder;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Repository.PseudoBaseDatosUsuario;
import Models.Repository.RepoColaboradores;

import Service.Notificacion.Notificacion;
import Service.Notificacion.StrategyMedioDeNotificacion;
import Service.Validador.CredencialDeAcceso;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class HumanoController extends Controller  {


    public void create(Context context){

        context.render("persona-humana/registroHumana.hbs");

    }

    public void save(Context context){

        String nombre = context.formParam("nombre") ;
        String apellido = context.formParam("apellido") ;
     // LocalDate fechaNacimiento = LocalDate.parse(context.formParam("fecha_nacimiento"));
        String correo = context.formParam("correo");
        String nroTelefono = context.formParam("nroTelefono");
        String nroDocumento = context.formParam("documento");
        TipoDeDocumento tipoDeDocumento = TipoDeDocumento.valueOf(context.formParam("tipo_documento"));

        CredencialDeAccesoBuilder credencialDeAccesoBuilder = new CredencialDeAccesoBuilder();
        CredencialDeAcceso credencialDeAcceso = credencialDeAccesoBuilder
                .contrasenia(context.formParam("contrasenia"))
                .nombreUsuario(context.formParam("nombre_usuario"))
                .construir();

        FisicoBuilder fisicoBuilder = new FisicoBuilder();
        Fisico fisico = fisicoBuilder
                .nombre(nombre)
                .apellido(apellido)
             // .fechaNacimiento(fechaNacimiento)
                .correoElectronico(correo)
                .numeroDocumento(nroDocumento)
                .credencialDeAcceso(credencialDeAcceso)
                .tipoDocumento(tipoDeDocumento)
                .construir();

        ColaboradorBuilder colaboradorBuilder = new ColaboradorBuilder();
        Colaborador colaborador = colaboradorBuilder.construir(fisico);

        fisico.agregarRol(colaborador);
        fisico.setId(RandomGenerator.getDefault().nextInt(0,100));

        PseudoBaseDatosUsuario.getInstance().agregar(fisico);

        context.redirect("/login");
    }



    public void index(Context context){
        this.estaLogueado(context);

        context.render("index-inicio/index_Humana.hbs", this.basicModel(context));

    }


    public void show(Context context){
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);
        model.put("humano", model);

        context.render("persona-humana/perfilHumana.hbs", model);
    }


    public void update(Context context){
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        this.asignarParametros(context);

        context.redirect("/index/fisico");
    }

    public void asignarParametros(Context context){
         if ( context.formParam("medioNotificacion") != null ) {
             Notificacion medioDeNotificacion = StrategyMedioDeNotificacion.strategy( context.formParam("medioNotificacion") );
             System.out.println( context.formParam("codigo") );
             System.out.println( context.formParam("medioNotificacion"));
             getUsuario().setMedioDeNotificacion(medioDeNotificacion);
             getUsuario().setCodigoDeNotificacion(context.formParam("codigo"));
         }
    }


}
