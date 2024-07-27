package Service.Broker.Controllers;

import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Humano;
import Models.Domain.Tarjetas.SolicitudDeApertura;
import Models.Domain.Tarjetas.TarjetaAccesos;
import Service.SistemaDeGeolocalizacion.PseudoBaseDatosHeladera;
import org.json.JSONObject;

public class SolicitudApertura implements Publicacion {

    private PseudoBaseDatosHeladera base;
    private Colaborador colaborador;
    public void handleMessage(JSONObject jsonMessage) {
        String heladeraId = jsonMessage.getString("heladeraId");
        String usoTarjeta = jsonMessage.getString("value");
        base = new PseudoBaseDatosHeladera();
        colaborador = new Humano();
        Heladera heladera = base.baseHeladeras.stream().filter(f->f.getID() == Integer.parseInt(heladeraId) ).toList().get(0);

        SolicitudDeApertura solicitudApertura = new SolicitudDeApertura(TipoDonacion.valueOf(usoTarjeta),heladera);
        TarjetaAccesos tarjeta = colaborador.getTarjeta();
        tarjeta.agregarNuevaSolicitud(solicitudApertura);
        System.out.println("Solicitud " + solicitudApertura.getFechaApertura() );
        System.out.println("Tipo " + solicitudApertura.getAccion() );
        System.out.println("realizada " + solicitudApertura.getRealizada() );

    }


}

