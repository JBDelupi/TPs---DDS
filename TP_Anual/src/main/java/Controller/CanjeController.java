package Controller;

import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

public class CanjeController extends Controller {

    public void canjeExitoso(Context context) {
        this.estaLogueado(context);

        context.render("producto/canjeExitoso.hbs", this.basicModel(context));
    }
}
