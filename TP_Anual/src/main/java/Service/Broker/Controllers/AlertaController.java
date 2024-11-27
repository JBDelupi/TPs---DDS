package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Utilidades.TipoAlerta;
import Models.Repository.RepoHeladera;
import org.json.JSONObject;

public class AlertaController implements Publicacion {

    RepoHeladera repo = new RepoHeladera();


    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String alerta = jsonMessage.getString("value");

        Heladera heladera =  repo.buscar(Heladera.class, Integer.parseInt(heladeraId));
        heladera.generarIncidente(TipoAlerta.valueOf(alerta));
        repo.modificar(heladera);


    }
}
