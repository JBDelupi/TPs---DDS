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
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Repository.RepoHeladera;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class HeladeraController extends Controller implements ICrudViewsHandler {

    private RepoHeladera repo;

    public HeladeraController(RepoHeladera repo){
        this.repo = repo;
    }


    @Override
    public void create(Context context) {
        this.estaLogueado(context);

        context.render("heladera/registroHeladera.hbs",this.basicModel(context));
    }

    @Override
    public void save(Context context) {
        String calle = context.formParam("calle");
        String numero = context.formParam("numero");
        String localidad = context.formParam("localidad");
        int capacidadMaxima = Integer.parseInt(context.formParam("capacidad"));
        double temperaturaMax = Double.parseDouble(context.formParam("temperaturaMax"));
        double temperaturaMin = Double.parseDouble(context.formParam("temperaturaMin"));

        System.out.println(temperaturaMax);
        System.out.println(temperaturaMin);

        Direccion direccion = new Direccion();
        direccion.setNumero(numero);
        direccion.setLocalidad(localidad);
        direccion.setCalle(calle);

        HeladeraBuilder heladeraBuilder = new HeladeraBuilder();
        Heladera heladera = heladeraBuilder
                .abierto(true)
                .capacidadMaxima(capacidadMaxima)
                .temperaturaMax(temperaturaMax)
                .temperaturaMin(temperaturaMin)
                .Direccion(direccion)
                .construir();

        repo.agregar(heladera);

        context.redirect("/heladeras");
    }


    public void index(Context context){
        this.estaLogueado(context);

        List<Heladera> heladeraList = repo.buscarTodos();

        Map<String, Object> model = this.basicModel(context);

        model.put("heladeras",heladeraList);


        context.render("heladera/heladeras.hbs", model);

    }

    @Override
    public void show(Context context) {
        this.estaLogueado(context);

        // <-- HELADERA -->
        String id = context.pathParam("id");
        Heladera heladera = (Heladera) repo.buscar(Integer.parseInt(id));

        Alerta alerta = repo.ultimaAlerta(id);

        Map<String, Object> model = this.basicModel(context);

        model.put("heladera",heladera);
        model.put("alerta",alerta);
        model.put("hayAlerta", alerta != null);

        // <-- SUSCRIPCIONES -->
        String idPersona = context.sessionAttribute("idPersona");


        List<ObserverHeladera> suscriptores = heladera.getSuscriptores().stream()
                    .filter(f -> f.getColaborador().equals(context.formParam("IdPersona")))
                    .toList();

            model.put("suscriptores",suscriptores);



        context.render("heladera/detallesHeladera.hbs", model);

    }


    @Override
    public void update(Context context) {
        this.estaLogueado(context);

        String id = context.formParam("heladeraId");

        Heladera heladera = (Heladera) repo.buscar(Integer.parseInt(id));

        String idColaborador = context.formParam("idColaborador");

        List<ObserverHeladera> observerHeladeras = heladera.getSuscriptores().stream().filter(
                f->f.getColaborador().getId() == Integer.parseInt(idColaborador)
        ).toList();


        ObserverHeladera suscripcion = observerHeladeras.get(0);

        heladera.quitarSubscriptor(suscripcion);

        context.redirect("/heladeras/" + id);
    }

    @Override
    public void edit(Context context) {
        this.estaLogueado(context);

        String id = context.pathParam("id");
        Heladera heladera = (Heladera) repo.buscar(Integer.parseInt(id));

        String opcionSuscripcion = context.formParam("opcionSuscripcion");
        String numeroViandasStr = context.formParam("numeroViandas");

        int numeroViandas = numeroViandasStr != null && !numeroViandasStr.isEmpty() ? Integer.parseInt(numeroViandasStr) : 0;

        String idPersona = context.sessionAttribute("idPersona");

        switch (opcionSuscripcion) {
            case "faltanNViandas":
                FaltanNViandasParaLlenar suscripcion = new FaltanNViandasParaLlenar( (Persona) repo.search(Persona.class, context.sessionAttribute("IdPersona")) , numeroViandas);
                heladera.agregarSubscriptor(suscripcion);

                break;
            case "quedanNViandas":
                NViandasDisponibles suscripcion2 = new NViandasDisponibles( (Persona) repo.search(Persona.class, context.sessionAttribute("IdPersona"))  ,numeroViandas);
                heladera.agregarSubscriptor(suscripcion2);
                break;
            case "desperfecto":
                SufrioDesperfecto suscripcion3 = new SufrioDesperfecto( (Persona) repo.search(Persona.class, context.sessionAttribute("IdPersona")) );
                heladera.agregarSubscriptor(suscripcion3);
                break;
        }

        repo.modificar(heladera);
        context.redirect("/heladeras/"+id);
    }




}
