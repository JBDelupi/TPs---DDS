package Controller;

import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

public class IncidenteController extends Controller implements ICrudViewsHandler {
    @Override
    public void create(Context context) {

        context.render("Incidente/createIncidente.hbs");
    }

    @Override
    public void save(Context context) {


        context.redirect("/incidentes");
    }

    @Override
    public void index(Context context) {

        context.render("incidentes/index.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }
}
