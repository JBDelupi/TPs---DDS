package Controller.Administrador;

import Controller.Actores.RolUsuario;
import Models.Domain.Heladera.Suscripciones.*;
import Controller.Controller;
import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Repository.RepoHeladera;
import Service.APIPuntos.Punto;
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
        String longitud = context.formParam("longitud");
        String latitud = context.formParam("latitud");

        System.out.println(temperaturaMax);
        System.out.println(temperaturaMin);

        Direccion direccion = new Direccion();
        direccion.setNumero(numero);
        direccion.setLocalidad(localidad);
        direccion.setCalle(calle);
        Punto punto = new Punto(latitud, longitud);
        direccion.setCentro(punto);

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
        String idPersona = context.sessionAttribute("idPersona");

        Heladera heladera = (Heladera) repo.buscar(Integer.parseInt(id));
        Alerta alerta = repo.ultimaAlerta(id);


        Map<String, Object> model = this.basicModel(context);
        model.put("heladera",heladera);
        model.put("alerta",alerta);
        model.put("hayAlerta", alerta != null);


        List<ObserverHeladera> suscriptores = heladera.getSuscriptores().stream()
                    .filter(f -> f.getColaborador().equals(this.getUsuario()))
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

        String opcionSuscripcion = context.formParam("opcionSuscripcion");
        String numeroViandasStr = context.formParam("numeroViandas");

        int numeroViandas = numeroViandasStr != null && !numeroViandasStr.isEmpty() ? Integer.parseInt(numeroViandasStr) : 0;
        ObserverHeladera suscripcion = StrategySuscripcion.Strategy(opcionSuscripcion,numeroViandas,this.getUsuario());

        String id = context.pathParam("id");
        Heladera heladera = (Heladera) repo.buscar(Integer.parseInt(id));
        heladera.agregarSubscriptor(suscripcion);

        repo.modificar(heladera);
        context.redirect("/heladeras/"+id);
    }




}
