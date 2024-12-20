package Controller;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Persona;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Repository.RepoContribucion;
import Service.Notificacion.Mensaje.MensajeBienvenida;
import Service.Notificacion.Mensaje.MensajeCanje;
import Service.Observabilidad.MetricsRegistry;
import io.javalin.http.Context;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.List;
import java.util.Map;


public class ProductoController extends Controller {

    private final RepoContribucion repo;

    public ProductoController(RepoContribucion repo) {
        this.repo = repo;
    }


    public void index(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        List<OfrecerProducto> productos = repo.buscarTodos(OfrecerProducto.class);
        model.put("productos",productos);


        context.render("Producto/productosOfrecidos.hbs", model);
    }

    public void show(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        String id = context.pathParam("id");
        OfrecerProducto producto = repo.buscar(OfrecerProducto.class,Integer.parseInt(id));

        model.put("producto",producto);
        model.put("colaborador",getUsuario().getRol(TipoRol.COLABORADOR));

        context.render("Producto/show.hbs", model);
    }

    public void canjeExitoso(Context context) {

        String idProducto = context.formParam("idProducto");
        OfrecerProducto producto = repo.buscar(OfrecerProducto.class, Integer.parseInt(idProducto));
        Integer cantidad = Integer.valueOf(context.formParam("cantidadCanjear"));

        String id = context.sessionAttribute("idPersona");
        this.usuario = repo.buscar(Persona.class,Integer.parseInt(id));

        Colaborador colaborador = (Colaborador) getUsuario().getRol(TipoRol.COLABORADOR);
        colaborador.realizarCanje(producto,cantidad);

        new MensajeCanje(usuario.getCorreElectronico(), producto.getProducto().getNombre() + producto.getProducto().getDescripcion() );


        repo.modificar(colaborador);
        repo.modificar(producto);

        //Incremento la metrica
        MeterRegistry registry = MetricsRegistry.getInstance().getRegistry();
        registry.counter("dds.canjes").increment();

        context.render("Producto/canjeExitoso.hbs",this.basicModel(context));
    }

    public void historialCanjes(Context context) {
        this.estaLogueado(context);
        Map<String, Object> model = this.basicModel(context);

        Colaborador colaborador = ((Colaborador) getUsuario().getRol(TipoRol.COLABORADOR));
        model.put("canjes",colaborador.getHistorialCanje());

        context.render("Producto/historialCanjes.hbs", model);
    }

}
