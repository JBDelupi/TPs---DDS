package Controller;

import Models.Domain.Builder.IncidentesBuilder.FallaTecnicaBuilder;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Heladera.Incidentes.Incidente;
import Models.Domain.Personas.Actores.Fisico;
import Models.Repository.RepoIncidente;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class FallaTecnicaController extends Controller implements ICrudViewsHandler {

    private final RepoIncidente repo;

    public FallaTecnicaController(RepoIncidente repo) {
        this.repo = repo;
    }


    @Override
    public void create(Context context) {
        this.estaLogueado(context);

        List<Heladera> heladeras = repo.queryHeladera();
        Map<String, Object> model = this.basicModel(context);
        model.put("heladeras",heladeras);


        context.render("Incidentes/createIncidente.hbs", model);
    }

    @Override
    public void save(Context context) {
        String heladera = context.formParam("heladera");
        String descripcion = context.formParam("descripcion");
        String imagenAdjunta = context.formParam("imagenAdjunta");
        String idUsuario = context.sessionAttribute("idPersona");

        FallaTecnicaBuilder fallaTecnicaBuilder = new FallaTecnicaBuilder();
        FallaTecnica fallaTecnica = fallaTecnicaBuilder
                .heladera(repo.buscar(Heladera.class, Integer.parseInt(heladera)) )
                .colaborador( repo.buscar(Fisico.class,Integer.parseInt(idUsuario)) )
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

        List<FallaTecnica> fallasTecnicas = repo.buscarTodos(FallaTecnica.class);

        Map<String, Object> model = this.basicModel(context);
        model.put("fallasTecnicas",fallasTecnicas);


        context.render("Incidentes/index.hbs", model);
    }

    @Override
    public void show(Context context) {
        this.estaLogueado(context);

        String id = context.pathParam("id");

        FallaTecnica fallaTecnica = repo.buscar(FallaTecnica.class, Integer.parseInt(id));

        Map<String, Object> model = this.basicModel(context);

        model.put("fallaTecnica",fallaTecnica);

        context.render("Incidentes/show.hbs", model);
    }

    @Override
    public void edit(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);
        model.put("heladeras", repo.queryHeladera() );

        List<Incidente> incidentesAbiertos = repo.buscarTodos(FallaTecnica.class).stream()
                .filter(incidente -> ! incidente.getSolucionado())
                .collect(Collectors.toList());

        model.put("incidentesAbiertos", incidentesAbiertos);

        context.render("Incidentes/VisitaFallaTecnica.hbs",model);
    }

    @Override
    public void update(Context context) {
        this.estaLogueado(context);

        String descripcion = context.formParam("descripcion");
        String imagen = context.formParam("imagenAdjunta");
        String solucionadoStr = context.formParam("solucionado");
        Boolean solucionado = solucionadoStr.equals("si");
        String incidenteId = context.formParam("incidenteId");

        FallaTecnica incidente = repo.buscar(FallaTecnica.class,Integer.parseInt(incidenteId));
        incidente.crearRegistroDeVisita(this.usuario, descripcion,solucionado,imagen);

        repo.modificar(incidente);

        context.redirect("/incidentes");
    }

    public void verSeguimiento(Context context){
        this.estaLogueado(context);

        String id = context.sessionAttribute("idPersona");
        Fisico fisico = repo.buscar(Fisico.class,Integer.parseInt(id));
        Map<String, Object> model = this.basicModel(context);
        model.put("humano", fisico);


        context.render("Incidentes/seguimientoIncidente.hbs", model);
    }

}
