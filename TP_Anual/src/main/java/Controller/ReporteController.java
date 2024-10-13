package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Reporte.*;
import Models.Repository.PseudoBaseDatosFallaTecnica;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosProductosOfrecidos;
import Models.Repository.PseudoBaseDatosUsuario;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReporteController extends Controller implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        this.estaLogueado(context);

        Persona usuario = context.sessionAttribute("usuario");
        Map<String, Object> model = this.basicModel(context);
        model.put("usuario", usuario);
        model.put("esHumano", usuario.getTipoUsuario().compareTo(RolUsuario.FISICO));

        context.render("Reportes/index.hbs", model);
    }

    @Override
    public void show(Context context) {

    }


    @Override
    public void create(Context context) throws IOException {
        this.estaLogueado(context);

        List<Heladera> heladeras = PseudoBaseDatosHeladera.getInstance().getBaseHeladeras();
        List<Fisico> colaboradores = PseudoBaseDatosUsuario.getInstance().getColaboradoresYFisicos();

        String tipoReporte = context.queryParam("tipo");

        TemplateReporte reporte = StrategyReporte.strategy(tipoReporte);
        Map<String, Object> model = this.basicModel(context);

        List<Object[]> report;
        if (tipoReporte.equals("cantidadDeViandasPorColaborador")) {
            report = reporte.obtenerListado(colaboradores);
        } else {
            report = reporte.obtenerListado(heladeras);
        }
        model.put("reporte", report);

        context.render("Reportes/" + tipoReporte + ".hbs", model);


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
