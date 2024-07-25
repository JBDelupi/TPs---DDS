package Service.Broker;

import Service.Notificacion.Mensaje;
import org.json.JSONObject;

public class Alerta  implements  Publicacion{


    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String alerta = jsonMessage.getString("value");
        System.out.println("Alerta de " + heladeraId + ": " + alerta);
        // Aquí podrías registrar la alerta en una base de datos
    }
}
