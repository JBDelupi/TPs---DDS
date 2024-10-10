package Controller;

import Controller.Actores.RolUsuario;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Actores.Persona;
import Models.Repository.PseudoBaseDatosProductosOfrecidos;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

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

        String tipoReporte = context.queryParam("tipo");
        switch (tipoReporte) {
            case "movimientoDeViandasPorHeladera":
                break;
            case "cantidadDeFallasPorHeladera":
                break;
            case "cantidadDeViandasPorColaborador":
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
