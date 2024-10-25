package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import Models.Repository.EntityManager.EntityManagerHelper;
import org.json.JSONObject;

public class AlertaController implements Publicacion {


    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String alerta = jsonMessage.getString("value");

        Heladera heladera = EntityManagerHelper.getEntityManager().find(Heladera.class,heladeraId);
        heladera.generarIncidente(TipoAlerta.valueOf(alerta));

        EntityManagerHelper.getEntityManager().merge(heladera);

    }
}
