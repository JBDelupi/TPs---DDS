package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Reporte.CantFallasPorHeladera;
import Models.Domain.Reporte.CantViandasPorColaborador;
import Models.Domain.Reporte.MovimientoViandasPorHeladera;
import Models.Repository.PseudoBaseDatosFallaTecnica;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosProductosOfrecidos;
import Models.Repository.PseudoBaseDatosUsuario;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

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
    public void create(Context context) {
        this.estaLogueado(context);
        List<Heladera> heladeras = PseudoBaseDatosHeladera.getInstance().getBaseHeladeras();
        List<Fisico> colaboradores = PseudoBaseDatosUsuario.getInstance().getColaboradoresYFisicos();


        String tipoReporte = context.queryParam("tipo");
        switch (tipoReporte) {
            case "cantidadDeFallasPorHeladera":
                CantFallasPorHeladera generadorReporte = new CantFallasPorHeladera();
                List<Object[]> listaFallasPorHeladera = generadorReporte.obtenerListado(heladeras);
                Map<String, Object> model = this.basicModel(context);
                model.put("FallasHeladeras",listaFallasPorHeladera);
                context.render("Reportes/cantidadDeFallasPorHeladera.hbs", model);
                break;
            case "movimientoDeViandasPorHeladera":
                MovimientoViandasPorHeladera generadorReporteMov = new MovimientoViandasPorHeladera();
                List<Object[]> listaMovimientosDeViandasPorHeladera = generadorReporteMov.obtenerListado(heladeras);
                Map<String, Object> modelMov = this.basicModel(context);
                modelMov.put("MovimientoDeViandasHeladeras",listaMovimientosDeViandasPorHeladera);
                context.render("Reportes/movimientoDeViandasPorHeladera.hbs", modelMov);
                break;
            case "cantidadDeViandasPorColaborador":
                CantViandasPorColaborador cantidadViandasColaborador = new CantViandasPorColaborador();
                List<Object[]> listaViandasPorColaborador = cantidadViandasColaborador.obtenerListado(colaboradores);
                Map<String, Object> modelCol = this.basicModel(context);
                modelCol.put("Colaboradores",listaViandasPorColaborador);
                context.render("Reportes/cantidadDeViandasPorColaborador.hbs", modelCol);
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
