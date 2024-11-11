package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Repository.EntityManager.EntityManagerHelper;
import Models.Repository.RepoHeladera;
import org.json.JSONObject;

public class ActualizacionTemperaturaController implements Publicacion {

    RepoHeladera repoHeladera = new RepoHeladera(Heladera.class);

    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String temperatura = jsonMessage.getString("value");

        Heladera heladera = (Heladera) repoHeladera.buscar(Integer.parseInt(heladeraId) );
        heladera.setTemperaturaActual(Double.parseDouble(temperatura));
        repoHeladera.modificar(heladera);


    }
}
