package Controller;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.FaltanNViandasParaLlenar;
import Models.Domain.Heladera.Suscripciones.NViandasDisponibles;
import Models.Domain.Heladera.Suscripciones.SufrioDesperfecto;
import Models.Repository.PseudoBaseDatosHeladera;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

public class SuscripcionesController extends Controller implements ICrudViewsHandler {

    @Override
    public void index(Context context) {

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        this.estaLogueado(context);

        String id = context.pathParam("id");
        Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(id);

        String opcionSuscripcion = context.formParam("opcionSuscripcion");
        String numeroViandasStr = context.formParam("numeroViandas");
        int numeroViandas = numeroViandasStr != null && !numeroViandasStr.isEmpty() ? Integer.parseInt(numeroViandasStr) : 0;

        switch (opcionSuscripcion) {
            case "faltanNViandas":
                FaltanNViandasParaLlenar suscripcion = new FaltanNViandasParaLlenar(this.getUsuario(),numeroViandas);
                heladera.agregarSubscriptor(suscripcion);
                break;
            case "quedanNViandas":
                NViandasDisponibles suscripcion2 = new NViandasDisponibles(this.getUsuario(),numeroViandas);
                heladera.agregarSubscriptor(suscripcion2);
                break;
            case "desperfecto":
                SufrioDesperfecto suscripcion3 = new SufrioDesperfecto(this.getUsuario());
                heladera.agregarSubscriptor(suscripcion3);
                break;
        }

        context.redirect("/heladeras/"+id);

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }
}

