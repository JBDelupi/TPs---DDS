package Controller.Administrador;

import Controller.Controller;
import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Heladera.Heladera;
import Models.Repository.PseudoBaseDatosHeladera;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeladeraController extends Controller implements ICrudViewsHandler {

    // GET
    @Override
    public void create(Context context) {

        context.render("heladera/registroHeladera.hbs");
    }

    // POST CREATE
    @Override
    public void save(Context context) {
        String direccion = context.formParam("direccion") ;
//        int capacidadMaxima = Integer.parseInt(context.formParam("capacidad"));
//        double temperaturaMax = Double.parseDouble(context.formParam("TemperaturaMax"));
//        double temperaturaMin = Double.parseDouble(context.formParam("TemperaturaMin"));

        HeladeraBuilder heladeraBuilder = new HeladeraBuilder();
        Heladera heladera = heladeraBuilder
                .abierto(true)
//                .capacidadMaxima(capacidadMaxima)
//                .temperaturaMax(temperaturaMax)
//                .temperaturaMin(temperaturaMin)
                .construir();

        heladera.setId(10);

        PseudoBaseDatosHeladera.getInstance().agregar(heladera);

        // Guardar
        context.redirect("/heladeras");
    }


    // ALL - GET
    public void index(Context context){
        List<Heladera> heladeraList = PseudoBaseDatosHeladera.getInstance().baseHeladeras;


        Map<String, Object> model = new HashMap<>();

        model.put("heladeras",heladeraList);


        context.render("heladera/heladeras.hbs", model);

    }

    // SOLO - GET
    @Override
    public void show(Context context) {
        String id = context.queryParam("id");
        Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(id);

        Map<String, Object> model = new HashMap<>();

        model.put("heladera",heladera);

        context.render("heladera/detallesHeladera.hbs", model);

    }


    // GET
    @Override
    public void edit(Context context) {
        context.render("edit");
    }

    // POST
    @Override
    public void update(Context context) {

    }




}
