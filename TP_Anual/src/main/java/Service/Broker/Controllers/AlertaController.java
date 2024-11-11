package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import Models.Repository.EntityManager.EntityManagerHelper;
import Models.Repository.RepoHeladera;
import org.json.JSONObject;

public class AlertaController implements Publicacion {

    RepoHeladera repo = new RepoHeladera(Heladera.class);


    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String alerta = jsonMessage.getString("value");

        Heladera heladera = (Heladera) repo.buscar(Integer.parseInt(heladeraId));
        heladera.generarIncidente(TipoAlerta.valueOf(alerta));
        repo.modificar(heladera);


    }
}
