package Controller;

import Controller.DTO.CrearContribucionDTO;
import Models.Domain.FormasDeContribucion.ContribucionesGenerales.DonacionDeDinero;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.DistribucionDeViandas;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.DonacionDeVianda;
import Models.Domain.FormasDeContribucion.ContribucionesHumana.EntregaDeTarjeta;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.HacerseCargoDeHeladera;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.FormasDeContribucion.Utilidades.FactoryContribucion;
import Models.Domain.FormasDeContribucion.Utilidades.Contribucion;
import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Repository.RepoContribucion;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContribucionController extends Controller implements ICrudViewsHandler {

    private RepoContribucion repo;

    public ContribucionController(RepoContribucion repo){
        this.repo = repo;
    }


    @Override
    public void index(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        model.put("esHumano", this.usuario instanceof Fisico );

        context.render("FormasDeContribucion/index.hbs", model);

    }

    @Override
    public void show(Context context) {

    }


    @Override
    public void create(Context context) {
        this.estaLogueado(context);

        String tipoContribucion = context.queryParam("tipo");

        context.render("FormasDeContribucion/"+tipoContribucion+".hbs", this.obtenerModeloContribucion(tipoContribucion,context));

    }


    @Override
    public void save(Context context) {
        this.estaLogueado(context);

        Map<String, String> singleValueParams = context.formParamMap().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().get(0)
                ));

        CrearContribucionDTO dto = new CrearContribucionDTO(context.formParam("tipo"), singleValueParams );
        FactoryContribucion.getInstance().factoryMethod( context.sessionAttribute("idPersona") , dto );

        context.render("FormasDeContribucion/contribucionExitosa.hbs", this.basicModel(context));
    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    public void consultarContribuciones(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        List<Contribucion> contribuciones = repo.queryContribucion(context.sessionAttribute("idPersona"));
        List<String> detalles = new ArrayList<>();

        //TODO: ESTO ES UN ASCO, PERO FUNCIONA ASI QUE NO ROMPAN LOS HUEVOS. SI ALGUIEN QUIERE ROMPER
        // LOS HUEVOS ME LO DICE Y ROMPEMOS LOS HUEVOS

        for (Contribucion contribucion : contribuciones) {
            String unDetalle = "";
            switch (contribucion.getNombre()) {
                case "Donacion de Dinero":
                    unDetalle += "Monto: " + ((DonacionDeDinero) contribucion).getMonto().toString();
                    unDetalle += ", Frecuencia: " + ((DonacionDeDinero) contribucion).getFrecuencia().toString();
                    break;
                case "Distribucion de viandas":
                    unDetalle += "Heladera Origen: " + ((DistribucionDeViandas) contribucion).getHeladeraOrigen().getId();
                    unDetalle += ", Heladera Destino: " + ((DistribucionDeViandas) contribucion).getHeladeraDestino().getId();
                    unDetalle += ", Cantidad: " + ((DistribucionDeViandas) contribucion).getCantidadDeViandasAMover().toString();
                    unDetalle += ", Fecha de Donación: " + ((DistribucionDeViandas) contribucion).getFechaDeDonacion().toString();
                    break;
                case "Donacion de vianda":
                    unDetalle += "Vianda: " + ((DonacionDeVianda) contribucion).getVianda().getNombre();
                    unDetalle += ", Heladera: " + ((DonacionDeVianda) contribucion).getHeladera().getId();
                    break;
                case "Entrega de Tarjeta":
                    unDetalle += "Tarjeta Codigo: " + ((EntregaDeTarjeta) contribucion).getTarjeta().getCodigo();
                    break;
                case "Hacerse cargo de heladera":
                    unDetalle += "Heladera: " + ((HacerseCargoDeHeladera) contribucion).getHeladera().getId();
                    break;
                case "Ofrecer producto":
                    unDetalle += "Producto: " + ((OfrecerProducto) contribucion).getProducto().getNombre();
                    unDetalle += ", Puntos Necesarios: " + ((OfrecerProducto) contribucion).getPuntosNecesarios().toString();
                    unDetalle += ", Stock: " + ((OfrecerProducto) contribucion).getStock().toString();
                    break;
            }
            detalles.add(unDetalle);
        }

        model.put("contribuciones", contribuciones);
        model.put("detalles", detalles);
        context.render("FormasDeContribucion/misContribuciones.hbs", model);
    }


    private Map<String, Object> obtenerModeloContribucion(String tipoContribucion, Context context) {
        Map<String,Object> model = this.basicModel(context);
        switch (tipoContribucion) {
            case "donarViandas" -> {
                model.put("heladeras", repo.queryHeladeraDisponible());
                return model;
            }
            case "distribucionViandas" -> {
                List<Heladera> heladeraList = repo.queryHeladera();

                List<Heladera> heladerasConAlimentos = new ArrayList<>();
                List<Heladera> heladerasDisponibles = new ArrayList<>();

                for (Heladera heladera : heladeraList) {
                    if (!heladera.getViandas().isEmpty()) {
                        heladerasConAlimentos.add(heladera);
                    }
                    if (!heladera.getEstaLlena()) {
                        heladerasDisponibles.add(heladera);
                    }
                }

                model.put("heladeras", heladerasConAlimentos);
                model.put("heladerasDisponible", heladerasDisponibles);

                return model;
            }
            case "hacerseCargoHeladera" -> {
                model.put("heladeras", repo.queryHeladeraDuenia());
                return model;
            }
        }


        return model;

    }


}
