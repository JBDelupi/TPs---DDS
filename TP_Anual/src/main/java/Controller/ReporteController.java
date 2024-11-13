package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Reporte.*;
import Models.Repository.RepoReporte;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReporteController extends Controller {

    private RepoReporte repo;

    public ReporteController(RepoReporte repo) {
        this.repo = repo;
    }


    public void index(Context context) {
        this.estaLogueado(context);

        Persona usuario = context.sessionAttribute("usuario");
        Map<String, Object> model = this.basicModel(context);
        model.put("usuario", usuario);
        model.put("esHumano", usuario.getTipoUsuario().compareTo(RolUsuario.FISICO));


        context.render("Reportes/index.hbs", model);
    }

    public void show(Context context) {
        this.estaLogueado(context);

        String tipoReporte = context.queryParam("tipo");

        // busco en la base de datos los reportes del tipo seleccionado en el index.
        List<TemplateReporte> listaReportes = repo.buscarPorTipo(tipoReporte);
        Map<String, Object> model = this.basicModel(context);
        model.put("listaReportes", listaReportes);
        model.put("tipoReporte", tipoReporte);

        context.render("Reportes/listadoReportes.hbs", model);
    }


    public void reporte(Context context) throws IOException {
        this.estaLogueado(context);

        // en el listado de reportes por fecha, cuando le doy click a uno me pasa el id del reporte.
        String idReporte = context.formParam("idReporte");
        String tipoReporte = context.queryParam("tipo");

        // busco en la base de datos el reporte del idReporte que me pasaron por formParam.
        TemplateReporte reporte = (TemplateReporte) repo.buscar(Integer.parseInt(idReporte));
        Map<String, Object> model = this.basicModel(context);
        model.put("reporte",reporte);

        context.render("Reportes/" + tipoReporte + ".hbs", model);
    }


}
