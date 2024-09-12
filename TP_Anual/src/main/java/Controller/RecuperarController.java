package Controller;

import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

public class RecuperarController extends Controller {

    public void index(Context context) {

        context.render("sesion/recuperar.hbs");
    }

}
