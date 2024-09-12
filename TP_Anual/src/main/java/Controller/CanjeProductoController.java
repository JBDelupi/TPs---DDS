package Controller;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Producto.Canje;
import Models.Domain.Producto.Producto;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosProducto;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanjeProductoController extends Controller implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        this.estaLogueado(context);

        List<Producto> productoList = PseudoBaseDatosProducto.getInstance().baseProductos;

        Map<String, Object> model = this.basicModel(context);

        model.put("canjes", productoList);

        context.render("producto/canjearProducto.hbs", model);
    }
    @Override
    public void save(Context context){}

    @Override
    public void create(Context context){}

    @Override
    public void show(Context context){}

    @Override
    public void edit(Context context) {
        context.render("edit");
    }

    @Override
    public void update(Context context) {

    }
}
