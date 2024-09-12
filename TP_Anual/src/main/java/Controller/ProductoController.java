package Controller;

import Models.Domain.Builder.ContribucionBuilder.OfrecerProductoBuilder;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Producto.Producto;
import Models.Domain.Producto.TipoRubro;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosProducto;
import Models.Repository.PseudoBaseDatosProductosOfrecidos;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.random.RandomGenerator;

public class ProductoController extends Controller implements ICrudViewsHandler {

    public void create(Context context) {
        this.basicModel(context);

        context.render("FormasDeContribucion/ofrecerProducto.hbs",this.basicModel(context));
    }

    public void save(Context context) {
        String nombre = context.formParam("nombreProducto");
        String imagen = context.formParam("imagenProducto");
        String descripcion = context.formParam("descripcionProducto");
        String rubroProducto = context.formParam("rubroProducto");
        Double precio = Double.parseDouble(context.formParam("precioProducto"));
        Integer stock = Integer.parseInt(context.formParam("stock"));

        TipoRubro tipoRubro = TipoRubro.valueOf(rubroProducto);

        if(imagen.isEmpty()){imagen = "/images/producto-test.png";}
        Producto producto = new Producto(tipoRubro, nombre, imagen, descripcion);
        producto.setId(RandomGenerator.getDefault().nextInt());

        OfrecerProducto productoOfrecido = new OfrecerProducto();
        productoOfrecido.setID(producto.getId());
        productoOfrecido.setProducto(producto);
        productoOfrecido.setPuntosNecesarios(precio);
        productoOfrecido.setStock(stock);
        productoOfrecido.setFechaDeDonacion(LocalDate.now());

        System.out.println("Producto creado: "+ producto.getId() + tipoRubro);

        PseudoBaseDatosProducto.getInstance().agregar(producto);
        PseudoBaseDatosProductosOfrecidos.getInstance().agregar(productoOfrecido);


        context.redirect("/productos");
    }

    public void index(Context context) {
        this.estaLogueado(context);

        List<OfrecerProducto> productos = PseudoBaseDatosProductosOfrecidos.getInstance().getBaseProductosOfrecidos();

        Map<String, Object> model = this.basicModel(context);

        model.put("productos",productos);

        context.render("producto/productosOfrecidos.hbs", model);
    }

    public void show(Context context) {
        this.estaLogueado(context);

        Map<String, Object> model = this.basicModel(context);

        String id = context.pathParam("id");
        OfrecerProducto producto = PseudoBaseDatosProductosOfrecidos.getInstance().getOfrecerProductoById(id);

        model.put("producto",producto);

        context.render("producto/show.hbs", model);
    }

    public void edit(Context context) {
        context.render("edit");
    }


    public void update(Context context) {

    }

}
