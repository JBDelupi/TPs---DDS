package Controller;


import Service.APIPuntos.Punto;
import Service.APIPuntos.ServicioPuntosAPI;
import io.javalin.http.Context;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PuntoCercanoController extends Controller {

    public void index(Context context){
        this.estaLogueado(context);

        context.render("Punto-cercano/formularioPunto.hbs",this.basicModel(context));
    }


    public void cargarPuntos(Context context) throws IOException {
        this.estaLogueado(context);

        String latitud = context.formParam("latitud");
        String longitud = context.formParam("longitud");
        String radio = context.formParam("radio");

        List<Punto> puntoList = ServicioPuntosAPI.getInstance().obtenerPuntosEstrategicos(new Punto(latitud,longitud), radio);

        Map<String, Object> model = this.basicModel(context);

        model.put("puntos", puntoList);

        context.render("Punto-cercano/puntocercano.hbs",model);
    }



}
