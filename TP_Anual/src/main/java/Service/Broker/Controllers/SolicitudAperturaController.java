package Service.Broker.Controllers;
/*
import Models.Domain.FormasDeContribucion.Utilidades.TipoDonacion;
import Models.Domain.Heladera.Heladera;
import Models.Domain.Personas.Actores.Colaborador;
import Models.Domain.Personas.Actores.Fisico;
import Models.Domain.Personas.Actores.TipoRol;
import Models.Domain.Tarjetas.SolicitudDeApertura;
import Models.Domain.Tarjetas.TarjetaAccesos;
import Service.Broker.RabbitMQAdapter;
import org.json.JSONObject;

public class SolicitudAperturaController implements Publicacion {

    private Fisico fisico;
    private final String routingKey = "heladera.autorizacion";

    public void handleMessage(JSONObject jsonMessage) {
        try {
            // Obtener información del mensaje
            String heladeraId = jsonMessage.getString("heladeraId");
            String usoTarjeta = jsonMessage.getString("value");
            /*
            // Buscar el usuario asociado a la tarjeta
            fisico = PseudoBaseDatosUsuario.getInstance().searchUserTarjeta(usoTarjeta);

            // Obtener la heladera
            Heladera heladera = PseudoBaseDatosHeladera.getInstance().getId(heladeraId);

            // Verificar si ya hay una solicitud de apertura para esa heladera y colaborador
            boolean aperturaAutorizada = verificarSolicitudDeApertura(heladera, fisico);

            // Enviar respuesta "OK" si la apertura está autorizada
            enviarAutorizacionApertura(heladeraId, aperturaAutorizada);

            if (aperturaAutorizada) {
                System.out.println("Autorización OK enviada para heladera ID: " + heladeraId);
            } else {
                System.out.println("Autorización DENEGADA para heladera ID: " + heladeraId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private boolean verificarSolicitudDeApertura(Heladera heladera, Fisico fisico) {
        // Obtener el rol de colaborador de la persona
        Colaborador colaborador = (Colaborador) fisico.getRol(TipoRol.COLABORADOR);

        // Revisar si existe una solicitud de apertura para la heladera
        TarjetaAccesos tarjeta = colaborador.getTarjeta();
        for (SolicitudDeApertura solicitud : tarjeta.getSolicitudesDeApertura()) {
            if (solicitud.getHeladera().equals(heladera) && !solicitud.getRealizada()) {
                return true;  // Hay una solicitud pendiente de apertura
            }
        }
        return false;// No hay solicitudes pendientes de apertura
    }

    private void enviarAutorizacionApertura(String heladeraId, boolean autorizada) throws Exception {
        // Crear un JSON simple con el ID de la heladera y si la apertura fue autorizada
        JSONObject autorizacionJson = new JSONObject();
        autorizacionJson.put("heladeraId", heladeraId);
        autorizacionJson.put("status", autorizada ? "ok" : "denegado");

        // Usar RabbitMQAdapter para enviar la autorización
        RabbitMQAdapter.getInstance().publish(routingKey, autorizacionJson);
    }

  }

 */

