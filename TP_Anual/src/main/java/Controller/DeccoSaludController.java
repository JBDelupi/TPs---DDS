package Controller;


import Models.Repository.RepoSalud;
import Service.DeccoSaludAPI.DTO.Reporte.ReporteSalud;
import io.javalin.http.Context;
import java.util.List;
import java.util.Map;


public class DeccoSaludController extends Controller {
    private final RepoSalud repoSalud;

    public DeccoSaludController(RepoSalud repoSalud) {
        this.repoSalud = repoSalud;
    }

    public void index(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        List<ReporteSalud> r = repoSalud.buscarTodos(ReporteSalud.class);
        model.put("localidades",r);

        context.render("Decco-salud-API/personasVulnerablesDeccoSalud.hbs", model);
    }

    public void mostrarReporte(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);


        String idReporte = context.formParam("idReporte");
        ReporteSalud reporte = repoSalud.buscar(ReporteSalud.class, Integer.parseInt(idReporte));

        model.put("reporte", reporte);

        context.render("Decco-salud-API/detallesReporteSalud.hbs", model);
    }

}

