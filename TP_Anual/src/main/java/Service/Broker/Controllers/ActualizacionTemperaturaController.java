package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Repository.EntityManager.EntityManagerHelper;
import org.json.JSONObject;

public class ActualizacionTemperaturaController implements Publicacion {



    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String temperatura = jsonMessage.getString("value");
        Heladera heladera = EntityManagerHelper.getEntityManager().find(Heladera.class, heladeraId);
        heladera.setTemperaturaActual(Double.parseDouble(temperatura));
        heladera.getSensorTemperatura().activar();
        EntityManagerHelper.getEntityManager().merge(heladera);

    }
}
