package Service.Broker;

import Models.Domain.Heladera.Heladera;
import Service.Notificacion.Mensaje;
import Service.SistemaDeGeolocalizacion.PseudoBaseDatosHeladera;
import org.json.JSONObject;

public class ActualizacionTemperatura implements Publicacion{

    private PseudoBaseDatosHeladera base;

    public ActualizacionTemperatura(){
        base = new PseudoBaseDatosHeladera();
    }

    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String temperatura = jsonMessage.getString("value");
        System.out.println("Temperatura de " + heladeraId + ": " + temperatura);
        Heladera heladera = base.baseHeladeras.stream().filter(f->f.getID() == Integer.parseInt(heladeraId) ).toList().get(0);
       // heladera.getSensorTemperatura().activar();
        heladera.setTemperaturaActual(Double.parseDouble(temperatura));
    }
}
