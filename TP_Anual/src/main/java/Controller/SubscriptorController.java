package Controller;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Suscripciones.ObserverHeladera;
import Models.Repository.PseudoBaseDatosHeladera;
import io.javalin.http.Context;

public class SubscriptorController extends Controller {

    public void subscribe(Context context) {
        this.estaLogueado(context);
       // String idHeladera = context.queryParam("id_heladera");
       // String tipoSubscripcion = context.queryParam("tipo_subscripcion");

       // Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(idHeladera);
       //  Observer nuevoSubscritor = FactorySubscripcion.selecciona(tipoSubscripcion);
        // heladera.agregarSubscriptor(nuevoSubscritor);


        context.redirect("/subscriptor");
    }

}

