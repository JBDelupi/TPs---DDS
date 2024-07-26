package Service.Broker.Controllers;

import org.json.JSONObject;

public class SolicitudApertura implements Publicacion {


    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String usoTarjeta = jsonMessage.getString("value");


    }


}

