package Controller;

import Models.Domain.Builder.UsuariosBuilder.HumanoBuilder;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Repository.PseudoBaseDatosUsuario;
import Models.Repository.RepoColaboradores;

import Service.Validador.CredencialDeAcceso;
import io.javalin.http.Context;
import java.time.LocalDate;
import java.util.HashMap;
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

        humano.setId(RandomGenerator.getDefault().nextInt());
        CredencialDeAcceso credencialDeAcceso = new CredencialDeAcceso(context.formParam("nombre_usuario"), "1");
        humano.setCredencialDeAcceso(credencialDeAcceso);

        PseudoBaseDatosUsuario.getInstance().agregar(humano);

        System.out.println("usuario creado: "+ humano.getId());

        context.redirect("/");
    }

    public void index(Context context){



        context.render("persona-humana/Contribuciones_Humana.hbs");
    }
    public void show(Context context){

        String id = context.sessionAttribute("idPersona");
        Humano humano = (Humano) PseudoBaseDatosUsuario.getInstance().getId(id);

        Map<String, Object> model = new HashMap<>();

        model.put("humano",humano);


        context.render("persona-humana/perfilHumana.hbs", model);
    }


    public void edit(Object ... Context){

    }
    public void update(Object ... Context){

    }
    public void delete(Object ... Context){

    }


}
