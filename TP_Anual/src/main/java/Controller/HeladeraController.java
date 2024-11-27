package Controller;

import Controller.Actores.RolUsuario;
import Controller.DTO.CrearContribucionDTO;
import Models.Domain.Builder.ContribucionBuilder.HacerseCargoDeHeladeraBuilder;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.FormasDeContribucion.Utilidades.FactoryContribucion;
import Models.Domain.FormasDeContribucion.Utilidades.Model.ContribucionStrategyFactory;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Heladera.Suscripciones.*;
import Models.Domain.Builder.HeladeraBuilder;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Suscripciones.Utilidades.StrategySuscripcion;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Personas.DatosPersonales.Direccion;
import Models.Repository.RepoHeladera;
import Service.APIPuntos.Punto;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HeladeraController extends Controller implements ICrudViewsHandler {

    private final RepoHeladera repo;

    public HeladeraController(RepoHeladera repo){
        this.repo = repo;
    }


    @Override
    public void create(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);
        model.put("esAdmin", this.usuario.getTipoUsuario().equals(RolUsuario.ADMINISTRADOR));

        context.render("Heladera/registroHeladera.hbs",model);
    }

    @Override
    public void save(Context context) {
        this.estaLogueado(context);

        String calle = context.formParam("calle");
        String numero = context.formParam("numero");
        String localidad = context.formParam("localidad");
        int capacidadMaxima = Integer.parseInt(context.formParam("capacidad"));
        double temperaturaMax = Double.parseDouble(context.formParam("temperaturaMax"));
        double temperaturaMin = Double.parseDouble(context.formParam("temperaturaMin"));
        String longitud = context.formParam("longitud");
        String latitud = context.formParam("latitud");
        String hacerseCargoParam = context.formParam("hacerseCargo");
        boolean hacerseCargo = "si".equalsIgnoreCase(hacerseCargoParam);

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

        if(hacerseCargo){
            Map<String, String> singleValueParams = context.formParamMap().entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> entry.getValue().get(0)
                    ));

            singleValueParams.putIfAbsent("heladeraId", String.valueOf(heladera.getId()));

            CrearContribucionDTO dto = new CrearContribucionDTO("HACERSE_CARGO_DE_HELADERA", singleValueParams);
            FactoryContribucion.getInstance().factoryMethod( context.sessionAttribute("idPersona") , dto );
        }

        context.redirect("/heladeras");
    }


    public void index(Context context){
        this.estaLogueado(context);

        List<Heladera> heladeraList = repo.buscarTodos(Heladera.class);

        Map<String, Object> model = this.basicModel(context);
        model.put("esHumano", this.getUsuario().getTipoUsuario().equals(RolUsuario.FISICO));
        model.put("heladeras",heladeraList);



        context.render("Heladera/heladeras.hbs", model);

    }

    @Override
    public void show(Context context) {
        this.estaLogueado(context);

        // <-- HELADERA -->
        String id = context.pathParam("id");

        Heladera heladera = repo.buscar(Heladera.class,Integer.parseInt(id));
        Alerta alerta = repo.ultimaAlerta(id);

        List<ObserverHeladera> suscriptores = heladera.getSuscriptores().stream()
                    .filter(f -> f.getColaborador().equals(this.getUsuario()))
                    .toList();

        Map<String, Object> model = this.basicModel(context);
        model.put("heladera",heladera);
        model.put("alerta",alerta);
        model.put("hayAlerta", alerta != null);
        model.put("suscriptores",suscriptores);



        context.render("Heladera/detallesHeladera.hbs", model);

    }


    @Override
    public void update(Context context) {
        this.estaLogueado(context);
        String idSuscripcion = context.formParam("idSuscripcion");
        String idHeladera = context.formParam("heladeraId");

        ObserverHeladera suscripcion = repo.buscar(ObserverHeladera.class, Integer.parseInt(idSuscripcion));

        repo.eliminar(suscripcion);

        context.redirect("/heladeras/" + idHeladera);
    }

    @Override
    public void edit(Context context) {
        this.estaLogueado(context);

        String opcionSuscripcion = context.formParam("opcionSuscripcion");
        String numeroViandasStr = context.formParam("numeroViandas");
        String id = context.pathParam("id");


        ObserverHeladera suscripcion = StrategySuscripcion.Strategy(opcionSuscripcion,numeroViandasStr,this.getUsuario());
        Heladera heladera = repo.buscar(Heladera.class,Integer.parseInt(id));

        heladera.agregarSubscriptor(suscripcion);
        repo.modificar(heladera);

        context.redirect("/heladeras/"+id);
    }

    public void mostrarMisHeladeras(Context context){
        this.estaLogueado(context);

        List<Heladera> heladeraList = repo.buscarMisHeladeras(getUsuario().getId());

        Map<String, Object> model = this.basicModel(context);
        model.put("heladeras",heladeraList);

        context.render("Heladera/mis-heladeras.hbs", model);
    }


    public void mostrarEstadoHeladera(Context context) {
        this.estaLogueado(context);

        String idHeladera = context.pathParam("id");

        Heladera heladera = repo.buscar(Heladera.class, Integer.parseInt(idHeladera));

        List<FallaTecnica> fallasTecnicas = repo.buscarFallasPorHeladera(Integer.parseInt(idHeladera));

        Map<String, Object> model = this.basicModel(context);
        model.put("heladera", heladera);
        model.put("fallasTecnicas", fallasTecnicas);

        context.render("Heladera/estadoHeladera.hbs", model);
    }

    public void cambiarEstadoHeladera(Context context) {
        this.estaLogueado(context);

        String idHeladera = context.pathParam("id");

        Heladera heladera = repo.buscar(Heladera.class, Integer.parseInt(idHeladera));

        boolean nuevoEstado = Boolean.parseBoolean(context.formParam("estado"));

        heladera.setAbierto(nuevoEstado);
        repo.modificar(heladera);

        context.redirect("/heladeras/" + idHeladera + "/estado");

    }


    public void mostrarCantidadHeladerasActivas(Context context) {
        List<Heladera> heladeras = repo.buscarTodos(Heladera.class);

        long heladerasActivas = heladeras.size();

        Map<String, Object> model;

        if (context.sessionAttribute("usuario") == null) {
            model = new HashMap<>();
        } else {
            estaLogueado(context);
            model = this.basicModel(context);
            model.put("estaSesion",true);
        }
        model.put("cantidadHeladerasActivas", heladerasActivas);
        context.render("Main/index.hbs", model);
    }


}
