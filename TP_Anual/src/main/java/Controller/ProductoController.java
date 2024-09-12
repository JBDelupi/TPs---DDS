package Controller;

import Models.Domain.Builder.ContribucionBuilder.OfrecerProductoBuilder;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Producto.Producto;
import Models.Domain.Producto.TipoRubro;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosProducto;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class ProductoController extends Controller implements ICrudViewsHandler {

    public void create(Context context) {
        this.basicModel(context);

        context.render("producto/registroProducto.hbs",this.basicModel(context));
    }

    public void save(Context context) {
        String nombre = context.formParam("nombreProducto");
        String imagen = context.formParam("imagenProducto");
        String descripcion = context.formParam("descripcionProducto");
        String rubroProducto = context.formParam("rubroProducto");
        TipoRubro tipoRubro = TipoRubro.valueOf(rubroProducto);

        Producto producto = new Producto(tipoRubro,nombre,imagen,descripcion);
        producto.setId(RandomGenerator.getDefault().nextInt());

        System.out.println("Producto creado: "+ producto.getId() + tipoRubro);
        PseudoBaseDatosProducto.getInstance().agregar(producto);


        context.redirect("/productos");
    }

    public void index(Context context) {
        this.estaLogueado(context);

        List<Producto> productos = PseudoBaseDatosProducto.getInstance().baseProductos;

        Map<String, Object> model = this.basicModel(context);

        model.put("productos",productos);

        context.render("producto/productosOfrecidos.hbs", model);
    }

    public void show(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

        String id = context.pathParam("id");
        Producto producto = PseudoBaseDatosProducto.getInstance().getId(id);

        model.put("producto",producto);

        context.render("producto/show.hbs", model);
    }

    public void edit(Context context) {
        context.render("edit");
    }


    public void update(Context context) {

    }

}
