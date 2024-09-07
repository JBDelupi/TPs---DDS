package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Repository.PseudoBaseDatosHeladera;
import org.json.JSONObject;

public class ActualizacionTemperatura implements Publicacion {

    private PseudoBaseDatosHeladera base;

    public ActualizacionTemperatura(){
        base = new PseudoBaseDatosHeladera();
    }

    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String temperatura = jsonMessage.getString("value");
        Heladera heladera = base.baseHeladeras.stream().filter(f->f.getId() == Integer.parseInt(heladeraId) ).toList().get(0);
       // heladera.getSensorTemperatura().activar();
        heladera.setTemperaturaActual(Double.parseDouble(temperatura));

        System.out.println("Temperatura de " + heladeraId + ": " + temperatura);
    }
}
