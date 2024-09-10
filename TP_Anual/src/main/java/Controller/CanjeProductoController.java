package Controller;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Producto.Canje;
import Models.Repository.PseudoBaseDatosHeladera;
import Service.Server.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanjeProductoController extends Controller implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        //List<Canje> heladeraList = PseudoBaseDatosHeladera.getInstance().baseHeladeras;


        Map<String, Object> model = new HashMap<>();

        //model.put("heladeras", heladeraList);


        context.render("heladera/heladeras.hbs", model);
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
