package Controller.Administrador;

import Controller.Controller;
import io.javalin.http.Context;

public class AdministradorController extends Controller {



    public void index(Context context) {
        context.render("Adnministrador/Index_Admin.hbs");
    }

    public void getImportarColaborador(Context context) {
        context.render("heladera/Cargar_Tecnicos.hbs");
    }
    public void saveImportarColaborador(Context context) {

        context.redirect("/index");
    }

}
