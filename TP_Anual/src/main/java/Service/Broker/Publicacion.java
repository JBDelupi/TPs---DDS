package Service.Broker;

import Service.Notificacion.Mensaje;
import org.json.JSONObject;

public interface Publicacion {
    void handleMessage(JSONObject jsonMessage);
}
