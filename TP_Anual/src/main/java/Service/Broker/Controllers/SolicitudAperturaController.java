package Service.Broker.Controllers;

import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Tarjetas.SolicitudDeApertura;
import Models.Domain.Tarjetas.TarjetaAccesos;
import Models.Repository.PseudoBaseDatosHeladera;
import Models.Repository.PseudoBaseDatosUsuario;
import org.json.JSONObject;

public class SolicitudAperturaController implements Publicacion {

    private Fisico colaborador;
    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String usoTarjeta = jsonMessage.getString("value");

        colaborador = PseudoBaseDatosUsuario.getInstance().searchUserTarjeta(usoTarjeta);

        Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(heladeraId);

        SolicitudDeApertura solicitudApertura = new SolicitudDeApertura(TipoDonacion.valueOf(usoTarjeta),heladera);
        TarjetaAccesos tarjeta = colaborador.getTarjeta();
        tarjeta.agregarNuevaSolicitud(solicitudApertura);

        System.out.println("Solicitud " + solicitudApertura.getFechaApertura() );
        System.out.println("Tipo " + solicitudApertura.getAccion() );
        System.out.println("realizada " + solicitudApertura.getRealizada() );

    }


}

