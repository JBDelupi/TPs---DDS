package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Repository.EntityManager.EntityManagerHelper;
import Models.Repository.RepoHeladera;
import org.json.JSONObject;

public class ActualizacionTemperaturaController implements Publicacion {

    RepoHeladera repoHeladera = new RepoHeladera();

    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String temperatura = jsonMessage.getString("value");

        Heladera heladera = repoHeladera.buscar(Heladera.class,Integer.parseInt(heladeraId) );
        heladera.setTemperaturaActual(Double.parseDouble(temperatura));
        repoHeladera.modificar(heladera);


    }
}
