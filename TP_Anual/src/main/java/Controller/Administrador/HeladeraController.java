package Controller.Administrador;

import Controller.Actores.RolUsuario;
import Models.Domain.Heladera.Suscripciones.FaltanNViandasParaLlenar;
import Models.Domain.Heladera.Suscripciones.NViandasDisponibles;
import Models.Domain.Heladera.Suscripciones.ObserverHeladera;
import Controller.Controller;
import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Suscripciones.SufrioDesperfecto;
import Models.Repository.PseudoBaseDatosAlerta;
import Models.Repository.PseudoBaseDatosHeladera;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class HeladeraController extends Controller implements ICrudViewsHandler {

    // GET
    @Override
    public void create(Context context) {
        this.estaLogueado(context);

        context.render("heladera/registroHeladera.hbs",this.basicModel(context));
    }

    // POST CREATE
    @Override
    public void save(Context context) {
        String calle = context.formParam("calle");
        String numero = context.formParam("numero");
        String localidad = context.formParam("localidad");
        int capacidadMaxima = Integer.parseInt(context.formParam("capacidad"));
        double temperaturaMax = Double.parseDouble(context.formParam("TemperaturaMax"));
        double temperaturaMin = Double.parseDouble(context.formParam("TemperaturaMin"));

        HeladeraBuilder heladeraBuilder = new HeladeraBuilder();
        Heladera heladera = heladeraBuilder
                .abierto(true)
                .capacidadMaxima(capacidadMaxima)
                .temperaturaMax(temperaturaMax)
                .temperaturaMin(temperaturaMin)
                .calle(calle)
                .localidad(localidad)
                .numero(numero)
                .construir();

        heladera.setId(RandomGenerator.getDefault().nextInt(0,1));

        PseudoBaseDatosHeladera.getInstance().agregar(heladera);

        // Guardar
        context.redirect("/heladeras");
    }


    // ALL - GET
    public void index(Context context){
        this.estaLogueado(context);


        List<Heladera> heladeraList = PseudoBaseDatosHeladera.getInstance().getBaseHeladeras();

        Map<String, Object> model = this.basicModel(context);

        model.put("heladeras",heladeraList);
        model.put("esHumano",this.getUsuario().getTipoUsuario().equals(RolUsuario.FISICO));

        context.render("heladera/heladeras.hbs", model);

    }

    // SOLO - GET
    @Override
    public void show(Context context) {
        this.estaLogueado(context);

        // <-- HELADERA -->
        String id = context.pathParam("id");
        Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(id);
        Alerta alerta = PseudoBaseDatosAlerta.getInstance().ultimaAlerta(id);

        Map<String, Object> model = this.basicModel(context);

        model.put("heladera",heladera);
        model.put("alerta",alerta);
        model.put("hayAlerta", alerta != null);

        // <-- SUSCRIPCIONES -->
        List<ObserverHeladera> suscriptores = heladera.getSuscriptores().stream()
                .filter(s -> s.getColaborador().getId().equals(getUsuario().getId()))
                .toList();

        List<Map<String, Object>> suscriptoresConClase = suscriptores.stream().map(s -> {
            Map<String, Object> suscriptorMap = new HashMap<>();
            suscriptorMap.put("suscriptor", s);
            suscriptorMap.put("className", s.getClass().getSimpleName());
            return suscriptorMap;
        }).collect(Collectors.toList());

        model.put("suscripciones",suscriptoresConClase);


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




}
