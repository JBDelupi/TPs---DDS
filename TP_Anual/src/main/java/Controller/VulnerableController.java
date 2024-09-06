package Controller;

import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

public class VulnerableController extends Controller implements ICrudViewsHandler {
    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

        context.render("Heladeras/createVulnerable.hbs");
    }

    @Override
    public void save(Context context) {
        // TODA LA INFO
        context.redirect("/");
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }
}
