package Controller;

import Controller.Actores.Rol;
import Controller.Actores.TipoRol;
import Models.Domain.Builder.UsuariosBuilder.HumanoBuilder;
import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Repository.PseudoBaseDatosUsuario;
import Models.Repository.RepoColaboradores;

import Service.Validador.CredencialDeAcceso;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.random.RandomGenerator;

public class HumanoController extends Controller  {
    public HumanoController() {

    }


    public void save(Object ... Args) {

        String nombre = (String) Args[0];
        String apellido = (String) Args[1];
        LocalDate fechaNacimiento = (LocalDate) Args[2];
        String correo = (String) Args[3];
        String nroDocumento = (String) Args[4];
        TipoDeDocumento tipoDeDocumento = (TipoDeDocumento) Args[5];

        HumanoBuilder humanoBuilder = new HumanoBuilder();
        Humano humano = humanoBuilder
                .nombre(nombre)
                .apellido(apellido)
                .fechaNacimiento(fechaNacimiento)
                .correoElectronico(correo)
                .numeroDocumento(nroDocumento)
                .tipoDocumento(tipoDeDocumento)
                .construir();

        RepoColaboradores.getInstance().agregarColaborador(humano);
    }


    // @GET
    public void create(Context context){

        context.render("persona-humana/registroHumana.hbs");

    }

    // @POST
    public void save(Context context){

        String nombre = context.formParam("nombre") ;
        String apellido = context.formParam("apellido") ;
     //   LocalDate fechaNacimiento = LocalDate.parse(context.formParam("fecha_nacimiento"));
        String correo = context.formParam("correo");
        String nroTelefono = context.formParam("nroTelefono");
        String nroDocumento = context.formParam("documento");
        TipoDeDocumento tipoDeDocumento = TipoDeDocumento.valueOf(context.formParam("tipo_documento"));

        HumanoBuilder humanoBuilder = new HumanoBuilder();
        Humano humano = humanoBuilder
                .nombre(nombre)
                .apellido(apellido)
             //   .fechaNacimiento(fechaNacimiento)
                .correoElectronico(correo)
                .numeroDocumento(nroDocumento)
                .tipoDocumento(tipoDeDocumento)
                .construir();

        humano.setId(RandomGenerator.getDefault().nextInt(0,100));
        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso(context.formParam("nombre_usuario"), "1");
        humano.setCredencialDeAcceso(credencialDeAcceso);
        humano.setRol(new Rol(TipoRol.HUMANO));
        PseudoBaseDatosUsuario.getInstance().agregar(humano);



        System.out.println("usuario creado: "+ humano.getId());

        context.redirect("/login");
    }



    public void index(Context context){
        this.estaLogueado(context);

        context.render("index-inicio/index_Humana.hbs", this.basicModel(context));

    }

    public void consultarContribuciones(Context context){
        this.estaLogueado(context);

        Humano usuario = (Humano) PseudoBaseDatosUsuario.getInstance().getId(context.sessionAttribute("idPersona"));
        List<FormaDeContribucion> contribuciones = usuario.getFormaDeContribucion();

        Map<String, Object> model = this.basicModel(context);
        model.put("contribuciones",contribuciones);

        context.render("FormasDeContribucion/misContribuciones.hbs",model);
    }

    public void show(Context context){
        this.estaLogueado(context);

        String id = context.sessionAttribute("idPersona");
        Humano humano = (Humano) PseudoBaseDatosUsuario.getInstance().getId(id);
        Map<String, Object> model = this.basicModel(context);
        model.put("humano", humano);


        context.render("persona-humana/perfilHumana.hbs", model);
    }





}
