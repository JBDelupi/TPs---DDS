package Controller;

import Controller.DTO.CrearContribucionDTO;
import Models.Domain.FormasDeContribucion.Utilidades.FactoryContribucion;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Repository.RepoContribucion;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContribucionController extends Controller {

    private final RepoContribucion repo;

    public ContribucionController(RepoContribucion repo){
        this.repo = repo;
    }


    public void index(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);
        model.put("esHumano", this.usuario instanceof Fisico );
        context.render("Formas-de-contribucion/index.hbs", model);

    }


    public void create(Context context) {
        this.estaLogueado(context);
        String tipoContribucion = context.queryParam("tipo");
        context.render("Formas-de-contribucion/"+tipoContribucion+".hbs", this.obtenerModeloContribucion(tipoContribucion,context));

    }


    public void save(Context context) {
        this.estaLogueado(context);

        Map<String, String> singleValueParams = context.formParamMap().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().get(0)
                ));

        CrearContribucionDTO dto = new CrearContribucionDTO(context.formParam("tipo"), singleValueParams );
        FactoryContribucion.getInstance().factoryMethod( context.sessionAttribute("idPersona") , dto );

        context.render("Formas-de-contribucion/contribucionExitosa.hbs", this.basicModel(context));
    }



    public void consultarContribuciones(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        List<Contribucion> contribuciones = repo.queryContribucion(context.sessionAttribute("idPersona"));
        List<String> detalles = contribuciones.stream().map(f->f.getDetalle()).collect(Collectors.toList());

        model.put("contribuciones", contribuciones);
        model.put("detalles", detalles);
        context.render("Formas-de-contribucion/misContribuciones.hbs", model);
    }


    private Map<String, Object> obtenerModeloContribucion(String tipoContribucion, Context context) {
        Map<String,Object> model = this.basicModel(context);
        switch (tipoContribucion) {
            case "donarViandas" -> {
                model.put("heladeras", repo.queryHeladeraDisponible());
                return model;
            }
            case "distribucionViandas" -> {
                List<Heladera> heladeraList = repo.queryHeladera();

                List<Heladera> heladerasConAlimentos = new ArrayList<>();
                List<Heladera> heladerasDisponibles = new ArrayList<>();

                for (Heladera heladera : heladeraList) {
                    if (!heladera.getViandas().isEmpty()) {
                        heladerasConAlimentos.add(heladera);
                    }
                    if (!heladera.getEstaLlena()) {
                        heladerasDisponibles.add(heladera);
                    }
                }

                model.put("heladeras", heladerasConAlimentos);
                model.put("heladerasDisponible", heladerasDisponibles);

                return model;
            }
            case "hacerseCargoHeladera" -> {
                model.put("heladeras", repo.queryHeladeraDuenia());
                return model;
            }
        }


        return model;

    }


}
