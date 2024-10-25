package Controller;

import Models.Domain.Builder.IncidentesBuilder.FallaTecnicaBuilder;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Personas.Actores.Fisico;
import Models.Repository.RepoIncidente;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class FallaTecnicaController extends Controller implements ICrudViewsHandler {

    private RepoIncidente repo;

    public FallaTecnicaController(RepoIncidente repo) {
        this.repo = repo;
    }


    @Override
    public void create(Context context) {
        this.estaLogueado(context);

        List<Heladera> heladeras = repo.queryHeladera();
        Map<String, Object> model = this.basicModel(context);
        model.put("heladeras",heladeras);


        context.render("incidentes/createIncidente.hbs", model);
    }

    @Override
    public void save(Context context) {
        String heladera = context.formParam("heladera");
        String descripcion = context.formParam("descripcion");
        String imagenAdjunta = context.formParam("imagenAdjunta");

        FallaTecnicaBuilder fallaTecnicaBuilder = new FallaTecnicaBuilder();
        FallaTecnica fallaTecnica = fallaTecnicaBuilder
                .heladera((Heladera) repo.search(Heladera.class, heladera))
                .colaborador( (Fisico) repo.search(Fisico.class, context.sessionAttribute("idPersona")))
                .descripcion(descripcion)
                .foto(imagenAdjunta)
                .fecha(LocalDateTime.now())
                .construir();

        repo.agregar(fallaTecnica);


        context.redirect("/incidentes");
    }

    @Override
    public void index(Context context) {
        this.estaLogueado(context);

        List<FallaTecnica> fallasTecnicas = repo.buscarTodos();

        Map<String, Object> model = this.basicModel(context);
        model.put("fallasTecnicas",fallasTecnicas);


        context.render("incidentes/index.hbs", model);
    }

    @Override
    public void show(Context context) {
        this.estaLogueado(context);

        String id = context.pathParam("id");

        FallaTecnica fallaTecnica = (FallaTecnica) repo.buscar(Integer.parseInt(id));

        Map<String, Object> model = this.basicModel(context);

        model.put("fallaTecnica",fallaTecnica);

        context.render("incidentes/show.hbs", model);
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    public void verSeguimiento(Context context){
        this.estaLogueado(context);

        String id = context.sessionAttribute("idPersona");
        Fisico fisico = (Fisico) repo.search(Fisico.class,id);
        Map<String, Object> model = this.basicModel(context);
        model.put("humano", fisico);


        context.render("incidentes/seguimientoIncidente.hbs", model);
    }

}
