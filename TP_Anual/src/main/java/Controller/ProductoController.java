package Controller;

import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Producto.Canje;
import Models.Repository.RepoContribucion;
import io.javalin.http.Context;
import java.util.List;
import java.util.Map;


public class ProductoController extends Controller {

    private RepoContribucion repo;

    public ProductoController(RepoContribucion repo) {
        this.repo = repo;
    }


    public void index(Context context) {
        this.estaLogueado(context);

        List<OfrecerProducto> productos = repo.buscarTodos();

        Map<String, Object> model = this.basicModel(context);
        model.put("productos",productos);

        context.render("producto/productosOfrecidos.hbs", model);
    }

    public void show(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

        String id = context.pathParam("id");
        OfrecerProducto producto = (OfrecerProducto) repo.buscar(Integer.parseInt(id));

        model.put("producto",producto);
        model.put("colaborador",getUsuario().getRol(TipoRol.COLABORADOR));

        context.render("producto/show.hbs", model);
    }

    public void canjeExitoso(Context context) {
        this.estaLogueado(context);

        String idProducto = context.formParam("idProducto");
        OfrecerProducto producto = (OfrecerProducto) repo.buscar(Integer.parseInt(idProducto));
        Integer cantidad = Integer.valueOf(context.formParam("cantidadCanjear"));


        Colaborador colaborador = (Colaborador) getUsuario().getRol(TipoRol.COLABORADOR);
        colaborador.realizarCanje(producto,cantidad);

        repo.modificar(colaborador);

        context.render("producto/canjeExitoso.hbs");
    }

    public void historialCanjes(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

        Colaborador colaborador = ((Colaborador) getUsuario().getRol(TipoRol.COLABORADOR));
        List<Canje> canjes = colaborador.getHistorialCanje();

        model.put("canjes",canjes);

        context.render("producto/historialCanjes.hbs", model);
    }

}
