package Controller;

import Models.Domain.Builder.ContribucionBuilder.OfrecerProductoBuilder;
import Models.Domain.FormasDeContribucion.ContribucionesJuridicas.OfrecerProducto;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Rol;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Producto.Canje;
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
import java.util.Objects;
import java.util.random.RandomGenerator;

public class ProductoController extends Controller {

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
        model.put("colaborador",getUsuario().getRol(TipoRol.COLABORADOR));

        context.render("producto/show.hbs", model);
    }

    public void canjeExitoso(Context context) {
        this.estaLogueado(context);

        String idProducto = context.formParam("idProducto");
        OfrecerProducto producto = PseudoBaseDatosProductosOfrecidos.getInstance().getOfrecerProductoById(idProducto);
        Integer cantidad = Integer.valueOf(context.formParam("cantidadCanjear"));

        basicModel(context);

        ((Colaborador) getUsuario().getRol(TipoRol.COLABORADOR)).realizarCanje(producto, cantidad);

        context.render("producto/canjeExitoso.hbs");
    }


}
