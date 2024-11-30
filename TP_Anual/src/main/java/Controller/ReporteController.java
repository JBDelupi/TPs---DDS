package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Reporte.*;
import Models.Repository.RepoReporte;
import Service.Observabilidad.MetricsRegistry;
import io.javalin.http.Context;
import io.micrometer.core.instrument.MeterRegistry;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReporteController extends Controller {

    private final RepoReporte repo;

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

        List<TemplateReporte> listaReportes = repo.buscarPorTipo(tipoReporte);
        Map<String, Object> model = this.basicModel(context);
        model.put("listaReportes", listaReportes);
        model.put("tipoReporte", tipoReporte);

        //Incremento la metrica
        MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
        registry.counter("dds.consultasAReportes").increment();

        context.render("Reportes/listadoReportes.hbs", model);
    }


    public void reporte(Context context) throws IOException {
        this.estaLogueado(context);

        String idReporte = context.formParam("idReporte");
        String tipoReporte = context.queryParam("tipo");

        TemplateReporte reporte = repo.buscar(TemplateReporte.class ,Integer.parseInt(idReporte));
        Map<String, Object> model = this.basicModel(context);
        model.put("reporte",reporte);

        context.render("Reportes/" + tipoReporte + ".hbs", model);
    }


}
