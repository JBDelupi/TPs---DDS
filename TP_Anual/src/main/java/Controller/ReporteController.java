package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Reporte.*;
import Models.Repository.RepoReporte;
import Service.Observabilidad.MetricsRegistry;
import Service.Server.App;
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
        Map<String, Object> model = this.basicModel(context);

        model.put("esHumano", usuario.getTipoUsuario().compareTo(RolUsuario.FISICO));


        context.render("Reportes/index.hbs", model);
    }

    public void show(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);


        String tipoReporte = context.queryParam("tipo");
        List<TemplateReporte> listaReportes = repo.buscarPorTipo(tipoReporte);
        model.put("listaReportes", listaReportes);
        model.put("tipoReporte", tipoReporte);

        //Incremento la metrica
        MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
        registry.counter("dds.consultasAReportes").increment();

        context.render("Reportes/listadoReportes.hbs", model);
    }


    public void reporte(Context context) throws IOException {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        String idReporte = context.formParam("idReporte");
        String tipoReporte = context.queryParam("tipo");

        TemplateReporte reporte = repo.buscar(TemplateReporte.class ,Integer.parseInt(idReporte));
        model.put("reporte",reporte);

        context.render("Reportes/" + tipoReporte + ".hbs", model);
    }

    public void generarManualmente(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        App.generacionManual();

        String rolTipo = usuario.getTipoUsuario().toString().toLowerCase();
        context.redirect("/index/" + rolTipo);
    }


}
