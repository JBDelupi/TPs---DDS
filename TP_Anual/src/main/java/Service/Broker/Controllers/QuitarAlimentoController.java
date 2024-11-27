package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Tarjetas.TarjetaAlimentar;
import Models.Domain.Tarjetas.Utilidades.TipoAccion;
import Models.Repository.RepoHeladera;
import org.json.JSONObject;

public class QuitarAlimentoController implements Publicacion {
    RepoHeladera repo = new RepoHeladera();


    @Override
    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String tarjeta = jsonMessage.getString("tarjeta");

        Heladera heladera = repo.buscar(Heladera.class,Integer.parseInt(heladeraId) );

        TarjetaAlimentar tarjetaAlimentar =  repo.buscar(TarjetaAlimentar.class,Integer.parseInt(tarjeta));

        tarjetaAlimentar.agregarNuevoUso(heladera, TipoAccion.QUITAR);

        repo.modificar(heladera);
        repo.modificar(tarjetaAlimentar);

    }
}
