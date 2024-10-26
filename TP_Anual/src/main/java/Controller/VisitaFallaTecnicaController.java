package Controller;


import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Repository.RepoIncidente;
import io.javalin.http.Context;
import java.util.Map;


public class VisitaFallaTecnicaController extends Controller   {

    private RepoIncidente repo;

    public VisitaFallaTecnicaController(RepoIncidente repo) {
        this.repo = repo;
    }


    public void create(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);
        model.put("heladeras", repo.queryHeladera() );
        model.put("incidentesAbiertos", repo.buscarTodos());

        context.render("incidentes/VisitaFallaTecnica.hbs",model);
    }

    public void save(Context context) {
        this.estaLogueado(context);

        String descripcion = context.formParam("descripcion");
        String imagen = context.formParam("imagenAdjunta");
        String solucionadoStr = context.formParam("solucionado");
        Boolean solucionado = solucionadoStr.equals("si") ? true : false;
        String incidenteId = context.formParam("incidenteId");

        FallaTecnica incidente = (FallaTecnica) repo.buscar(Integer.parseInt(incidenteId));
        incidente.crearRegistroDeVisita(this.usuario, descripcion,solucionado,imagen);

        repo.modificar(incidente);

        context.redirect("/incidentes");
    }


}
