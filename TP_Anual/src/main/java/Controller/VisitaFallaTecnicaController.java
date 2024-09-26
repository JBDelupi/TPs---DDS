package Controller;

import Models.Domain.Builder.IncidentesBuilder.FallaTecnicaBuilder;
import Models.Domain.Builder.IncidentesBuilder.VisitaTecnicaBuilder;
import Models.Domain.Heladera.Incidentes.Utils.RegistroVisitaTecnica;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import static java.lang.Integer.parseInt;

public class VisitaFallaTecnicaController extends Controller implements ICrudViewsHandler {

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        context.render("incidentes/VisitaFallaTecnica.hbs");
    }

    @Override
    public void save(Context context) {
        String descripcion = context.formParam("descripcion");
        String imagen = context.formParam("imagenAdjunta");
        String solucionadoStr = context.formParam("solucionado");
        Boolean solucionado = solucionadoStr.equals("si") ? true : false;

        // Hay que crear el registro dentro de FallaTecnica.
        // Ver como
        VisitaTecnicaBuilder visitaTecnicaBuilder = new VisitaTecnicaBuilder();
        RegistroVisitaTecnica registroVisitaTecnica = visitaTecnicaBuilder
                .visitaExitosa(solucionado)
                .descripcion(descripcion)
                .foto(imagen)
                .construir();



        context.redirect("/incidente/" + context.queryParam("incidenteId"));
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }
}
