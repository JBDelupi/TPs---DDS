package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Tarjetas.Tarjeta;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Domain.Tarjetas.TipoAccion;
import Models.Repository.RepoHeladera;
import org.json.JSONObject;

public class QuitarAlimentoController implements Publicacion {
    RepoHeladera repo = new RepoHeladera(Heladera.class);


    @Override
    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String tarjeta = jsonMessage.getString("tarjeta");

        Heladera heladera = (Heladera) repo.buscar(Integer.parseInt(heladeraId) );

        TarjetaAlimentar tarjetaAlimentar = (TarjetaAlimentar) repo.search(TarjetaAlimentar.class, tarjeta);

        tarjetaAlimentar.agregarNuevoUso(heladera, TipoAccion.QUITAR);

        repo.modificar(heladera);
        repo.modificar(tarjetaAlimentar);

    }
}
