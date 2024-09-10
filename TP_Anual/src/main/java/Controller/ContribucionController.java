package Controller;

import Controller.Actores.Usuario;
import Models.Domain.FormasDeContribucion.Utilidades.FactoryContribucion;
import Models.Domain.FormasDeContribucion.Utilidades.FormaDeContribucion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class ContribucionController extends Controller implements ICrudViewsHandler {

    public ContribucionController(Usuario usuario) {
        this.usuario = usuario;
    }
    public ContribucionController( ) {

    }



    public void save(Object ... Context){
        FactoryContribucion factoryContribucion = new FactoryContribucion((Colaborador) this.usuario);
        FormaDeContribucion nuevaContribucion = factoryContribucion.factoryMethod(Context);
        
        // GUARDAR BASE DE DATOS

    }




    @Override
    public void index(Context context) {
        if(context.sessionAttribute("usuario") != null){
        Persona usuario = context.sessionAttribute("usuario");
        Map<String, Object> model = new HashMap<>();
        model.put("usuario", usuario);
        model.put("rol",usuario.getRol());
        context.render("FormasDeContribucion/index.hbs", model);
        }
    }

    @Override
    public void show(Context context) {

    }

    public void contribucionExitosa(Context context) {
        context.render("contribucionExitosa.hbs");
    }

    @Override
    public void create(Context context) {
        String tipoContribucion = context.queryParam("tipo");

        switch (tipoContribucion) {
            case "donarViandas":
                context.render("FormasDeContribucion/donacionDeViandas.hbs");
                break;
            case "donarDinero":
                context.render("FormasDeContribucion/donacionDeDinero.hbs");
                break;
            case "distribucionViandas":
                context.render("FormasDeContribucion/distribucionDeViandas.hbs");
                break;
        }
    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }


}
