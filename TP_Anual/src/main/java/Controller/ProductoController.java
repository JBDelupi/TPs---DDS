package Controller;

import Models.Domain.Builder.ContribucionBuilder.OfrecerProductoBuilder;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Producto.Producto;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosProducto;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController extends Controller implements ICrudViewsHandler {

    public void create(Context context) {

        context.render("producto/registroProducto.hbs");
    }

    public void save(Context context) {
        String producto = context.formParam("producto");
        Double puntosNecesarios = Double.parseDouble(context.formParam("puntos_necesarios"));
        Integer stock = Integer.parseInt(context.formParam("stock"));

        OfrecerProductoBuilder productoBuilder = new OfrecerProductoBuilder();
        OfrecerProducto ofrecerProducto = productoBuilder
                .puntosNecesarios(puntosNecesarios)
                .stock(stock)
                .construir();

        ofrecerProducto.setID(10);

        PseudoBaseDatosProducto.getInstance().agregar(ofrecerProducto);

        context.redirect("/producto");
    }

    public void index(Context context) {
        List<OfrecerProducto> ofrecerProductosList = PseudoBaseDatosProducto.getInstance().baseProductos;

        Map<String, Object> model = new HashMap<>();

        model.put("productos",ofrecerProductosList);

        context.render("producto/productosOfrecidos.hbs", model);
    }

    public void show(Context context) {
        String id = context.queryParam("id");
        OfrecerProducto ofrecerProducto = PseudoBaseDatosProducto.getInstance().getId(id);

        Map<String, Object> model = new HashMap<>();

        model.put("producto",ofrecerProducto);

        context.render("producto/detallesProducto", model);
    }

    public void edit(Context context) {
        context.render("edit");
    }


    public void update(Context context) {

    }

}
