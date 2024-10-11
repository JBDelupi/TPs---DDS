package Controller;

import Controller.Actores.RolUsuario;
import Controller.DTO.CrearContribucionDTO;
import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.FormasDeContribucion.Utilidades.FactoryContribucion;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Models.Repository.Dao;
import Models.Repository.PseudoBaseDatosUsuario;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.Map;
import java.util.stream.Collectors;

public class ContribucionController extends Controller implements ICrudViewsHandler {

    public ContribucionController(Persona usuario) {
        this.usuario = usuario;
    }
    public ContribucionController( ) {

    }



    @Override
    public void index(Context context) {
        this.estaLogueado(context);

        Persona usuario = context.sessionAttribute("usuario");
        Map<String, Object> model = this.basicModel(context);
        model.put("usuario", usuario);
        model.put("esHumano", usuario.getTipoUsuario().compareTo(RolUsuario.FISICO));
        context.render("FormasDeContribucion/index.hbs", model);

    }

    @Override
    public void show(Context context) {

    }

    public void contribucionExitosa(Context context) {
        this.estaLogueado(context);
        context.render("FormasDeContribucion/contribucionExitosa.hbs", this.basicModel(context));
    }

    @Override
    public void create(Context context) {
        this.estaLogueado(context);
        String tipoContribucion = context.queryParam("tipo");
        context.render("FormasDeContribucion/"+tipoContribucion+".hbs", this.basicModel(context));

    }


    @Override
    public void save(Context context) {

        Map<String, String> singleValueParams = context.formParamMap().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().get(0) // Toma el primer valor de la lista
                ));

        CrearContribucionDTO dto = new CrearContribucionDTO(context.formParam("tipo"), singleValueParams );


        FactoryContribucion factoryContribucion = new FactoryContribucion(PseudoBaseDatosUsuario.getInstance().getId("1"), null,null);
        factoryContribucion.generarDonacion(dto);

        context.render("FormasDeContribucion/contribucionExitosa.hbs", this.basicModel(context));
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }




}
