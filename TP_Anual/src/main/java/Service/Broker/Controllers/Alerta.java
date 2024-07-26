package Service.Broker.Controllers;

import Models.Domain.Heladera.Heladera;
import Models.Domain.Heladera.Incidentes.FallaTecnica;
import Models.Domain.Personas.Actores.Humano;
import Service.SistemaDeGeolocalizacion.PseudoBaseDatosHeladera;
import org.json.JSONObject;

public class Alerta  implements Publicacion {

    private PseudoBaseDatosHeladera base;

    public Alerta(){
        base = new PseudoBaseDatosHeladera();
    }


    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String alerta = jsonMessage.getString("value");

        System.out.println("Alerta de " + heladeraId + ": " + alerta);
        // Aquí podrías registrar la alerta en una base de datos
        Heladera heladera = base.baseHeladeras.stream().filter(f->f.getID() == Integer.parseInt(heladeraId) ).toList().get(0);
        FallaTecnica incidente = new FallaTecnica(heladera,new Humano());
        incidente.setDescripcion(alerta);
        System.out.println("Falla tecnica avisada " + incidente.toString() + "Falla ->" + incidente.getDescripcion());

    }
}
