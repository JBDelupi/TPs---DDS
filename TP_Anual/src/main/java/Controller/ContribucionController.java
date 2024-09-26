package Controller;

import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.FormasDeContribucion.Utilidades.FactoryContribucion;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.Map;

public class ContribucionController extends Controller implements ICrudViewsHandler {

    public ContribucionController(Usuario usuario) {
        this.usuario = usuario;
    }
    public ContribucionController( ) {

    }



    public void save(Object ... Context){
        FactoryContribucion factoryContribucion = new FactoryContribucion((Colaborador) this.usuario);
        Contribucion nuevaContribucion = factoryContribucion.factoryMethod(Context);
        
        // GUARDAR BASE DE DATOS

    }




    @Override
    public void index(Context context) {
        this.estaLogueado(context);

        Persona usuario = context.sessionAttribute("usuario");
        Map<String, Object> model = this.basicModel(context);
        model.put("usuario", usuario);
        model.put("esHumano", usuario.getRol().getTipo().equals(TipoRol.HUMANO));
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

        switch (tipoContribucion) {
            case "donarViandas":
                context.render("FormasDeContribucion/donacionDeViandas.hbs", this.basicModel(context));
                break;
            case "donarDinero":
                context.render("FormasDeContribucion/donacionDeDinero.hbs", this.basicModel(context));
                break;
            case "distribucionViandas":
                context.render("FormasDeContribucion/distribucionDeViandas.hbs", this.basicModel(context));
                break;
            case "registrarPersonaVulnerable":
                context.render("FormasDeContribucion/registroVulnerable.hbs", this.basicModel(context));
                break;
            case "hacerseCargoHeladera":
                context.render("FormasDeContribucion/hacerseCargoDeHeladera.hbs", this.basicModel(context));
                break;
            case "ofrecerProducto":
                context.render("FormasDeContribucion/ofrecerProducto.hbs", this.basicModel(context));
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
