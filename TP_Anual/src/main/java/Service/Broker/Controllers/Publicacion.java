package Service.Broker.Controllers;

import org.json.JSONObject;

public interface Publicacion {
    void handleMessage(JSONObject jsonMessage);
}
