package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.Alerta;
import Models.Domain.Heladera.Incidentes.Utils.TipoAlerta;
import Models.Repository.PseudoBaseDatosAlerta;
import Models.Repository.PseudoBaseDatosHeladera;
import org.json.JSONObject;

public class AlertaController implements Publicacion {


    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String alerta = jsonMessage.getString("value");

        Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(heladeraId);
        Alerta incidente = new Alerta(TipoAlerta.valueOf(alerta), heladera);
        heladera.notify(incidente);

        PseudoBaseDatosAlerta.getInstance().agregar(incidente);
    }
}
