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
import Models.Domain.Personas.DatosPersonales.Direccion;
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


    @Override
    public void index(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        model.put("colaborador", usuario.checkRol(TipoRol.COLABORADOR));
        model.put("tecnico", usuario.checkRol(TipoRol.TECNICO));

        context.render("index-inicio/index_Juridica.hbs", model);
    }

    @Override
    public void show(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

        context.render("persona-Juridica/perfilJuridico.hbs", model);
    }

    public void create(Context context){
        context.render("persona-Juridica/registroJuridica.hbs");
    }

    public void save(Context context) {

        String razonSocial =  context.formParam("razon_social") ;
        TipoJuridico tipoJuridico = TipoJuridico.valueOf(context.formParam("tipo_juridico"));
        String correo = context.formParam("email");
        String calle = context.formParam("calle");
        String numero = context.formParam("numero");
        String localidad = context.formParam("localidad");

        Direccion direccion = new Direccion();
        direccion.setCalle(calle);
        direccion.setNumero(numero);
        direccion.setLocalidad(localidad);


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
                .rol(new Colaborador())
                .sede(direccion)
                .construir();

        juridico.setId(RandomGenerator.getDefault().nextInt(0,100));
        PseudoBaseDatosUsuario.getInstance().agregar(juridico);

        context.redirect("/login");
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }


}
