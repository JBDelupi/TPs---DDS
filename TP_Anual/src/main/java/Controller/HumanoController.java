package Controller;

import Models.Domain.Builder.UsuariosBuilder.HumanoBuilder;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Personas.DatosPersonales.TipoDeDocumento;
import Models.Repository.RepoColaboradores;

import io.javalin.http.Context;
import java.time.LocalDate;

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

    public void index(Object ... Context){

    }
    public void show(Object ... Context){

    }

    // @GET
    public void create(Context context){

        context.render("registroHumana.hbs");

    }

    // @POST
    public void save(Context context){

        String nombre = context.formParam("nombre") ;
        String apellido = context.formParam("apellido") ;
        LocalDate fechaNacimiento = LocalDate.parse(context.formParam("fecha_nacimiento"));
        String correo = context.formParam("correo");
        String nroDocumento = context.formParam("documento");
        TipoDeDocumento tipoDeDocumento = TipoDeDocumento.valueOf(context.formParam("tipo_documento"));

        HumanoBuilder humanoBuilder = new HumanoBuilder();
        Humano humano = humanoBuilder
                .nombre(nombre)
                .apellido(apellido)
                .fechaNacimiento(fechaNacimiento)
                .correoElectronico(correo)
                .numeroDocumento(nroDocumento)
                .tipoDocumento(tipoDeDocumento)
                .construir();

     //   RepoColaboradores.getInstance().agregarColaborador(humano);


        context.redirect("/");
    }

    public void index(Context context){
        context.render("/indexHumano");
    }
    public void show(Context context){
        context.render("humana");
    }


    public void edit(Object ... Context){

    }
    public void update(Object ... Context){

    }
    public void delete(Object ... Context){

    }


}
